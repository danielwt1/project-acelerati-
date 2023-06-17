#!/bin/bash
# author: kaylee paez - kaylee.paez@pragma.com.co

# Create a new task definition for this build
echo "Changing image env vars values in image-definitions"
sed -e "s;%IMAGE_VERSION%;${IMAGE_VERSION};g" \
    -e "s;%PORT%;${PORT};g" \
    -e "s;%CPU_CONTAINER%;${CPU_CONTAINER};g" \
    -e "s;%MEMORY_CONTAINER%;${MEMORY_CONTAINER};g" \
    -e "s;%TASK_FAMILY%;${TASK_FAMILY};g" \
    -e "s;%SERVICE_NAME%;${SERVICE_NAME};g" \
    -e "s;%IMAGE_NAME%;${IMAGE_NAME};g" \
    -e "s;%AWS_ACCOUNT_ID%;${AWS_ACCOUNT_ID};g" \
    ./pipeline/image-definitions.json > image-definitions-v_${IMAGE_VERSION}.json

aws ecs register-task-definition --family ${TASK_FAMILY} --cli-input-json file://image-definitions-v_${IMAGE_VERSION}.json  --region ${AWS_REGION}

SERVICES=`aws ecs describe-services --services ${SERVICE_NAME} --cluster ${AWS_ECS_CLUSTER} --region ${AWS_REGION} | egrep "failures" | tr "/" " " | awk '{print $2}' | sed 's/"$//'`

#Create or Update the service with the new task definition and desired count
echo "Creating or Updating service with the new task definition and desired count..."
TASK_REVISION=`aws ecs describe-task-definition --task-definition ${TASK_FAMILY} --region ${AWS_REGION} | egrep "revision" | tr "/" " " | awk '{print $2}' | sed 's/"$//'`
if [ "$SERVICES" == "[]" ]; then
  echo -e "----------------------------\entered existing service\n----------------------------"
  DESIRED_COUNT=`aws ecs describe-services --services ${SERVICE_NAME} --cluster ${AWS_ECS_CLUSTER} --region ${AWS_REGION} | egrep "desiredCount" | tr "/" " " | awk '{print $2}' | sed 's/,$//' | head -n 1`
  if [ ${DESIRED_COUNT} = "0" ]; then
    DESIRED_COUNT="1"
  else
    # Scale running task to 0 and wait for draining
    aws ecs update-service --cluster ${AWS_ECS_CLUSTER} --service ${SERVICE_NAME} --desired-count 0 --region ${AWS_REGION}
    sleep 60
  fi
  aws ecs update-service --cluster ${AWS_ECS_CLUSTER} --service ${SERVICE_NAME} --task-definition ${TASK_FAMILY}:${TASK_REVISION} --desired-count ${DESIRED_COUNT} --region ${AWS_REGION}
else

  echo -e "----------------------------\ncreating and registering target group\n----------------------------"
  TG_IP_ARN=`aws elbv2 create-target-group --name ati-ip-${PROJECT_NAME}-tg --protocol HTTP --port ${PORT} --target-type ip --vpc-id ${VPC_ID} --health-check-path '/actuator/health' --region ${AWS_REGION} | egrep 'TargetGroupArn' | tr "," " " | awk '{print $2}' | sed 's/"$//' | sed 's/"//g'`
  echo "Target group ARN: ${TG_IP_ARN}"

  TG_ALB_ARN=`aws elbv2 create-target-group --name ati-alb-${PROJECT_NAME}-tg --protocol TCP --port ${PORT} --target-type alb --vpc-id ${VPC_ID} --health-check-path "/actuator/health" --region ${AWS_REGION} | egrep 'TargetGroupArn' | tr "," " " | awk '{print $2}' | sed 's/"$//' | sed 's/"//g'`
  echo "Target group ARN: ${TG_ALB_ARN}"


  echo -e "----------------------------\ncreating listeners\n----------------------------"
  aws elbv2 create-listener --load-balancer-arn ${AWS_ALB_ARN} --protocol HTTP --port ${PORT} --default-actions Type=forward,TargetGroupArn=${TG_IP_ARN} --region ${AWS_REGION}
  aws elbv2 create-listener --load-balancer-arn ${AWS_NLB_ARN} --protocol TCP --port ${PORT} --default-actions Type=forward,TargetGroupArn=${TG_ALB_ARN} --region ${AWS_REGION}

  echo -e "----------------------------\nentered new service\n----------------------------"
  aws ecs create-service --cluster ${AWS_ECS_CLUSTER} --service-name ${SERVICE_NAME} --task-definition ${TASK_FAMILY}:${TASK_REVISION} --desired-count 1 --region ${AWS_REGION} --network-configuration "awsvpcConfiguration={subnets=[${AWS_SUBNET}],securityGroups=[${AWS_SECURITY_GROUP}]}" --load-balancers targetGroupArn=${TG_IP_ARN},containerName=${SERVICE_NAME},containerPort=${PORT}

  aws elbv2 register-targets --target-group-arn ${TG_ALB_ARN} --targets Id=${AWS_ALB_ARN} --region ${AWS_REGION}

fi

# Open port
for sgID in ${AWS_SG_PORT}; do
  echo "Checking security group for port ${PORT}..."
  aws ec2 authorize-security-group-ingress --group-id $sgID --protocol tcp --port ${PORT} --cidr 0.0.0.0/0 --region ${AWS_REGION}
done

exit 0
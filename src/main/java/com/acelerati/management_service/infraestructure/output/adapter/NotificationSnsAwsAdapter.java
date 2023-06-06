package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.spi.NotificationPort;
import com.acelerati.management_service.infraestructure.config.aws.SnsTopicCreator;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;


public class NotificationSnsAwsAdapter implements NotificationPort {
    private static final String SUBJECT_SALE_CONFIRMATION="Confirmation of sale to the customer";

    @Value("${aws.topicname}")
    private String topicName;
    private final SnsClient snsClient;
    private final SnsTopicCreator snsTopicCreator;

    public NotificationSnsAwsAdapter(SnsClient snsClient, SnsTopicCreator snsTopicCreator) {
        this.snsClient = snsClient;
        this.snsTopicCreator = snsTopicCreator;
    }

    @Override
    public void sendNotification(String message) {
        String topicArn = this.snsTopicCreator.createOrGetSNSTopic(topicName);
        //subscription to topic SNS
        snsClient.subscribe(request -> request
                .protocol("email")
                .endpoint("danyda98@gmail.com")
                .returnSubscriptionArn(true)
                .topicArn(topicArn));
        //publish message to topic SNS
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .subject(SUBJECT_SALE_CONFIRMATION)
                .build();
        this.snsClient.publish(publishRequest);
    }
}

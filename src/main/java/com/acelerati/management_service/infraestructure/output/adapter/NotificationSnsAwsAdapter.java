package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.spi.NotificationPort;
import com.acelerati.management_service.infraestructure.config.aws.SnsTopicCreator;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.ListSubscriptionsByTopicRequest;
import software.amazon.awssdk.services.sns.model.ListSubscriptionsByTopicResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.Subscription;

import java.util.List;


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
        subscribeToTopicIfNotSubscribed("sanpra1208@gmail.com", topicArn);
        //publish message to topic SNS
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .subject(SUBJECT_SALE_CONFIRMATION)
                .build();
        this.snsClient.publish(publishRequest);
    }

    public void subscribeToTopicIfNotSubscribed(String email, String topicArn) {
        ListSubscriptionsByTopicRequest listRequest = ListSubscriptionsByTopicRequest.builder()
                .topicArn(topicArn)
                .build();
        ListSubscriptionsByTopicResponse listResponse = snsClient.listSubscriptionsByTopic(listRequest);

        List<Subscription> subscriptions = listResponse.subscriptions();
        boolean isSubscribed = false;

        for (Subscription subscription : subscriptions) {
            if (subscription.endpoint().equals(email)) {
                isSubscribed = true;
                break;
            }
        }

        if (!isSubscribed) {
            snsClient.subscribe(request -> request
                    .protocol("email")
                    .endpoint(email)
                    .returnSubscriptionArn(true)
                    .topicArn(topicArn));
        }
    }
}

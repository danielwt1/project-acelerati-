package com.acelerati.management_service.infraestructure.config.aws;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;

import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.Optional;

public class SnsTopicCreator {

    private final SnsClient snsClient;

    public SnsTopicCreator(SnsClient snsClient) {
        this.snsClient = snsClient;
    }
    public String createOrGetSNSTopic(String topicName) {
        Optional<String> topicArn = getTopicArn(topicName);
        return topicArn.orElseGet(() -> createSNSTopic(topicName));
    }

    public Optional<String> getTopicArn(String topicName) {
        ListTopicsResponse listTopicsResponse = snsClient.listTopics(ListTopicsRequest.builder().build());
        return listTopicsResponse.topics().stream().map(Topic::topicArn)
                .filter(topicArn -> topicArn.endsWith(topicName)).findFirst();
    }

    public String createSNSTopic(String topicName) {
        CreateTopicResponse result = null;
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();
            result = snsClient.createTopic(request);
            return result.topicArn();

        } catch (SnsException e) {
            return "";
        }
    }
}

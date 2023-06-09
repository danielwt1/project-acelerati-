package com.acelerati.management_service.infraestructure.aws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class SQSClient {
    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String sqsEndpointUrl;
    private final SqsAsyncClient sqsAsyncClient;

    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(sqsEndpointUrl)
                .messageGroupId("purchase-request")
                .messageDeduplicationId(String.valueOf(System.currentTimeMillis()))
                .messageBody(message)
                .build();
        SendMessageResponse messageResponse = sqsAsyncClient.sendMessage(sendMessageRequest).join();
        log.debug("Message was uploaded to the AWS Queue with ID: {}", messageResponse.messageId());
    }
}

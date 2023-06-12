package com.acelerati.management_service.infraestructure.aws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SQSClient {
    //@Value("${spring.cloud.aws.sqs.endpoint}")
    private static final String sqsEndpointUrl = "https://sqs.us-east-2.amazonaws.com/724240023474/DispatchPurchaseRequest.fifo";
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

    public List<Message> pullMessages() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsEndpointUrl)
                .waitTimeSeconds(20)
                .build();
        return sqsAsyncClient.receiveMessage(receiveMessageRequest).join().messages();
    }

    public void deleteMessages(List<Message> messagesToDelete) {
        try {
            for (Message currentMessage : messagesToDelete) {
                DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                        .queueUrl(sqsEndpointUrl)
                        .receiptHandle(currentMessage.receiptHandle())
                        .build();
                log.debug("Deleting received messages from AWS...");
                DeleteMessageResponse deleteResponse = sqsAsyncClient.deleteMessage(deleteRequest).join();
                log.debug("Messages deleted successfully: {}", deleteResponse.toString());
            }
        } catch (SqsException e) {
            log.error(e.awsErrorDetails().errorMessage());
        }
    }
}

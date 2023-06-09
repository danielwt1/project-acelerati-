package com.acelerati.management_service.infraestructure.input.cronjob;

import com.acelerati.management_service.application.handler.PurchaseSpringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class IncomingPurchaseRequestsListener {
    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String sqsEndpointUrl;
    private final SqsAsyncClient sqsAsyncClient;
    private PurchaseSpringService purchaseSpringService;

    @Scheduled(fixedDelay = 20_000, initialDelay = 25_000)
    public void pullPurchaseRequests() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsEndpointUrl)
                .waitTimeSeconds(20)
                .build();
        List<Message> receivedMessages = sqsAsyncClient.receiveMessage(receiveMessageRequest).join().messages();
        log.debug("Messages received from the AWS queue: {}", receivedMessages.size());
    }
}

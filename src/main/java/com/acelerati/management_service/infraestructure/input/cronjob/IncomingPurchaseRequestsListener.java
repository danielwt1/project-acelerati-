package com.acelerati.management_service.infraestructure.input.cronjob;

import com.acelerati.management_service.application.handler.PurchaseSpringService;
import com.acelerati.management_service.infraestructure.aws.SQSClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class IncomingPurchaseRequestsListener {
    private final PurchaseSpringService purchaseSpringService;
    private final SQSClient sqsClient;

    @Scheduled(fixedDelay = 20_000, initialDelay = 25_000)
    public void pullPurchaseRequests() {
        List<Message> receivedMessages = sqsClient.pullMessages();
        log.debug("Messages received from the AWS queue: {}", receivedMessages.size());
        List<String> saleIds = receivedMessages.stream()
                .map(Message::body)
                .collect(Collectors.toList());
        sqsClient.deleteMessages(receivedMessages);
        purchaseSpringService.performSales(saleIds);
    }

}

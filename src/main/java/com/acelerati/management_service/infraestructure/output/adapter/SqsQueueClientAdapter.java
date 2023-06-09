package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.application.driven.QueueClientPort;
import com.acelerati.management_service.infraestructure.aws.SQSClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SqsQueueClientAdapter implements QueueClientPort {
    private final SQSClient sqsClient;
    @Override
    public void submitPurchaseRequest(Long idSale) {
        log.debug("Sending message to AWS SQS");
        sqsClient.sendMessage(idSale.toString());
    }
}

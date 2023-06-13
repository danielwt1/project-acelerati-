package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReportRepositoryImpl implements ReportRepository {
    private static final Logger logger = LoggerFactory.getLogger(ReportRepositoryImpl.class);
    private final DynamoDbClient dynamoClient;

    public ReportRepositoryImpl(DynamoDbClient dynamoClient) {
        this.dynamoClient = dynamoClient;
    }

    @Override
    public void putItemInTable(String tableName, String date, String dateVal, String sales, String salesVal) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(date, AttributeValue.builder().s(dateVal).build());
        item.put(sales, AttributeValue.builder().s(salesVal).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        try {
            PutItemResponse response = dynamoClient.putItem(request);
            logger.debug(" was successfully updated. The request id is : {}" , response.responseMetadata().requestId());

        } catch (ResourceNotFoundException e) {
            logger.error("Error: The Amazon DynamoDB table tableName");
            logger.error("Be sure that it exists and that you've typed its name correctly!");
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
        }
    }
}

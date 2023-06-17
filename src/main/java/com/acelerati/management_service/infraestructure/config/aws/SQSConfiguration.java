package com.acelerati.management_service.infraestructure.config.aws;

import com.acelerati.management_service.application.driven.QueueClientPort;
import com.acelerati.management_service.infraestructure.aws.SQSClient;
import com.acelerati.management_service.infraestructure.output.adapter.SqsQueueClientAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SQSConfiguration {
    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Bean
    public QueueClientPort queueClientPort() {
        return new SqsQueueClientAdapter(sqsClient());
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    public SQSClient sqsClient() {
        return new SQSClient(sqsAsyncClient());
    }
}

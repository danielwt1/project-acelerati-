package com.acelerati.management_service.infraestructure.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsBeansConfiguration {
    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public SnsClient snsClient() {
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        Region awsRegion = Region.of(region);
        return SnsClient.builder()
                .region(awsRegion)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
    @Bean
    public SnsTopicCreator getSnsTopicCreator() {
        return new SnsTopicCreator(snsClient());
    }

}

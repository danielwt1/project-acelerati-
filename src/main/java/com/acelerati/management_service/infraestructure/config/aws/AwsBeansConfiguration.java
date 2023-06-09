package com.acelerati.management_service.infraestructure.config.aws;

import com.acelerati.management_service.domain.spi.NotificationPort;
import com.acelerati.management_service.infraestructure.output.adapter.NotificationSnsAwsAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsBeansConfiguration {
    @Value("${aws.region}")
    private String region;

    @Bean
    public SnsClient snsClient() {
        Region awsRegion = Region.of(region);
        return SnsClient.builder()
                .region(awsRegion)
                .credentialsProvider(this.getProviderChain())
                .build();
    }
    @Bean
    public SnsTopicCreator getSnsTopicCreator() {
        return new SnsTopicCreator(snsClient());
    }
    @Bean
    public NotificationPort getNotificatrion(){
        return new NotificationSnsAwsAdapter(snsClient(),getSnsTopicCreator() );

    }
    private AwsCredentialsProviderChain getProviderChain() {
        return AwsCredentialsProviderChain.builder()
                .addCredentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .addCredentialsProvider(SystemPropertyCredentialsProvider.create())
                .addCredentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .addCredentialsProvider(ProfileCredentialsProvider.create())
                .addCredentialsProvider(ContainerCredentialsProvider.builder().build())
                .addCredentialsProvider(InstanceProfileCredentialsProvider.create())
                .build();
    }

}

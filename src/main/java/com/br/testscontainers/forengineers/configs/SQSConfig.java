package com.br.testscontainers.forengineers.configs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {

    @Value("${aws.local:false}")
    private boolean isLocal;

    @Value("${aws.endpoint:}")
    private String endpoint;

    @Value("${aws.region:}")
    private String region;

    @Bean
    public AmazonSQSAsync getAmazonSnsClient() {
        if (isLocal) {
            return  AmazonSQSAsyncClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                    .build();

        }
        return AmazonSQSAsyncClientBuilder.defaultClient();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

}

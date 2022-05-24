package br.com.tqi.dio.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfiguration {

    @Value("${aws.profile}")
    private String profile;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.queue.endpoint}")
    private String serviceEndpoint;

    @Bean
    public AWSCredentialsProvider credentials() {
        return new AWSCredentialsProviderChain(new ProfileCredentialsProvider(profile),
                InstanceProfileCredentialsProvider.getInstance());
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQS() {
        return AmazonSQSAsyncClientBuilder.standard().withCredentials(credentials())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, region))
                .build();
    }

}

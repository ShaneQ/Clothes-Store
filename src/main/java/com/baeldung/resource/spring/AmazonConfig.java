package com.baeldung.resource.spring;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.baeldung.resource.spring.properties.SCCBucketProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AmazonConfig {
    private final SCCBucketProperties properties;
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(properties.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}

package com.baeldung.resource.spring;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.baeldung.resource.spring.properties.SCCBucketProperties;
import com.baeldung.resource.spring.properties.SCCEmailProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AmazonConfig {

    private final SCCBucketProperties s3Properties;

    private final SCCEmailProperties emailProperties;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(s3Properties.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public AmazonSimpleEmailService email() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(emailProperties.getAccessKey(), emailProperties.getSecretKey());
        return
                AmazonSimpleEmailServiceClientBuilder.standard()
                        .withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                        .build();
    }
}

package com.baeldung.resource.spring.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "scc.bucket")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SCCBucketProperties {

    private String name;
    private String url;
    private String region;
    private String accessKey;
    private String secretKey;
}
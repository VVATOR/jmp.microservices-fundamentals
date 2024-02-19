package com.epam.learn.microservices.fundamental.services.resources.configurations.aws;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws.s3")
public class AwsS3ClientProperties {
    private String endpointUrl;
    private String region;
    private String accessKey;
    private String secretKey;
}

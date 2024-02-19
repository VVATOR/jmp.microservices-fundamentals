package com.epam.learn.microservices.fundamental.services.resources.configurations.aws;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.util.Objects;

@Configuration
public class AwsS3ClientConfig {

    private final AwsS3ClientProperties properties;

    @Autowired
    public AwsS3ClientConfig(AwsS3ClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    public S3Client buildS3Client() {
        final var endpointUrl = properties.getEndpointUrl();
        var builder = S3Client.builder()
                .credentialsProvider(DefaultCredentialsProvider.create());

        if (!endpointUrl.isBlank()) {
            builder = builder.endpointOverride(URI.create(endpointUrl));
        }

        var regionValue = Region.of(properties.getRegion());
        if (Objects.isNull(regionValue)) {
            regionValue = Region.US_EAST_1;
        }

        return builder
                .region(regionValue)
                .forcePathStyle(true)
                .build();
    }

}

package com.epam.learn.microservices.fundamental.processors.resource.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate(
            @Value("${retry.maxAttempts:1}") int maxAttempts,
            @Value("${retry.backOffPeriod:1000}") int backOffPeriod) {
        final var retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxAttempts);

        final var backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(backOffPeriod);

        return RetryTemplate.builder()
                .customPolicy(retryPolicy)
                .customBackoff(backOffPolicy)
                .build();
    }
}

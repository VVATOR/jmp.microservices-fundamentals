package com.epam.learn.microservices.fundamental.processors.resource.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class IssueInitiator {
    public void randomIssueThrowToInitiateRetry() {
        final var isPresentRandomIssueForRetry = ThreadLocalRandom.current().nextBoolean();
        if (isPresentRandomIssueForRetry) {
            throw new RuntimeException("Something happened. Retry!");
        }
    }
}

package com.epam.learn.microservices.fundamental.processors.resource.exceptions;

public class MetadataPreparationException extends RuntimeException {
    public MetadataPreparationException(String message, Exception e) {
        super(message, e);
    }
}

package com.epam.learn.microservices.fundamental.services.dto;

public record ResourceMetadataDto(String name, String artist, String album, String length, Integer resourceId, String year) {
}
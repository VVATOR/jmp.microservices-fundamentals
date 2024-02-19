package com.epam.learn.microservices.fundamental.common.dto;

public record MetadataDto(String name, String artist, String album, String length, Integer resourceId, String year) {
}
package com.epam.learn.microservices.fundamental.common.dto;

public record MetadataDto(Integer resourceId, String name, String artist, String album, String length, String year) {
}
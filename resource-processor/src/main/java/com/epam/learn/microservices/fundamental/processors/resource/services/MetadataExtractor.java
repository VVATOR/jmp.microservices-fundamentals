package com.epam.learn.microservices.fundamental.processors.resource.services;

import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;

import java.io.InputStream;

public interface MetadataExtractor {
    MetadataDto prepareMetadataDto(Integer resourceId, InputStream mp3InputStream);
}

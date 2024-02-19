package com.epam.learn.microservices.fundamental.services.resources.services;

import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.resources.models.ResourceEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ResourcesService {
    IdResponse addResources(String originalFilename, InputStream mp3InputStream);

    Optional<ResourceEntity> getResourceBinaryAudioDataById(Integer id);

    byte[] getObject(String key);

    DeletedIdsResponse deleteResourceByIds(List<Integer> id);
}
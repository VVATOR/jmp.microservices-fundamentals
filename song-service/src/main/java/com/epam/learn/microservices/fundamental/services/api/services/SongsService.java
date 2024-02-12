package com.epam.learn.microservices.fundamental.services.api.services;

import com.epam.learn.microservices.fundamental.services.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.services.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.dto.ResourceMetadataDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SongsService {
    IdResponse addSongMetadata(ResourceMetadataDto resourceMetadataDto);

    Optional<ResourceMetadataDto> getSongMetadataById(Integer id);

    DeletedIdsResponse deleteSongsByIds(List<Integer> id);
}

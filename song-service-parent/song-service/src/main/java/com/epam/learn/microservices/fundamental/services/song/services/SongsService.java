package com.epam.learn.microservices.fundamental.services.song.services;

import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SongsService {
    IdResponse addSongMetadata(MetadataDto resourceMetadataDto);

    Optional<MetadataDto> getSongMetadataById(Integer id);

    DeletedIdsResponse deleteSongsByIds(List<Integer> id);
}

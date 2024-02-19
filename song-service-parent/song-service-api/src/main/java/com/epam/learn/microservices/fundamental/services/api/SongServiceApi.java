package com.epam.learn.microservices.fundamental.services.api;


import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import org.springframework.http.ResponseEntity;

public interface SongServiceApi {

    ResponseEntity<IdResponse> postMetadata(MetadataDto metadata);

    ResponseEntity<MetadataDto> getSongMetadataById(Integer metadataId);

    ResponseEntity<DeletedIdsResponse> deleteSongsByIds(String id);

}

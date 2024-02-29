package com.epam.learn.microservices.fundamental.services.clients.feign;

import com.epam.learn.microservices.fundamental.services.api.SongServiceApi;
import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "song-service", url = "${SONG_SERVICE_URL}")
public interface SongServiceFeignClient extends SongServiceApi {

    @PostMapping(value = "/songs")
    ResponseEntity<IdResponse> postMetadata(@RequestBody MetadataDto metadata);

    @GetMapping("/songs/{id}")
    ResponseEntity<MetadataDto> getSongMetadataById(@PathVariable(name = "id") Integer metadataId);

    @DeleteMapping("/songs")
    ResponseEntity<DeletedIdsResponse> deleteSongsByIds(@RequestParam(name = "id") String id);
}
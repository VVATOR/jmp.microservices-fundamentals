package com.epam.learn.microservices.fundamental.services.song.controllers;


import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.services.api.SongServiceApi;
import com.epam.learn.microservices.fundamental.services.song.services.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("")
public class SongController implements SongServiceApi {
    private static final String IDS_PARAMETER_SEPARATOR = ",";
    private static final int VALID_CSV_LENGTH_RESTRICTION = 200;
    private final SongsService songsService;

    public SongController(@Autowired SongsService songsService) {
        this.songsService = songsService;
    }

    @PostMapping(value = "/songs")
    public ResponseEntity<IdResponse> postMetadata(@RequestBody MetadataDto metadata) {
        try {
            final var songResponse = songsService.addSongMetadata(metadata);
            return ResponseEntity.ok().body(songResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<MetadataDto> getSongMetadataById(@PathVariable(name = "id") Integer metadataId) {
        try {
            return songsService.getSongMetadataById(metadataId)
                    .map(song -> ResponseEntity.ok().body(song))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/songs")
    public ResponseEntity<DeletedIdsResponse> deleteSongsByIds(@RequestParam(name = "id") String id) {
        try {
            if (id.length() > VALID_CSV_LENGTH_RESTRICTION) {
                return ResponseEntity.internalServerError().build();
            }
            final var integerIds = Arrays
                    .stream(id.split(IDS_PARAMETER_SEPARATOR))
                    .map(Integer::valueOf)
                    .toList();
            final var deletedIdsResponse = songsService.deleteSongsByIds(integerIds);
            return ResponseEntity.ok().body(deletedIdsResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

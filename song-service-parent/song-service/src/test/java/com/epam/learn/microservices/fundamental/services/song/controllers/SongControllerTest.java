package com.epam.learn.microservices.fundamental.services.song.controllers;

import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.services.song.services.SongsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SongControllerTest {

    @Mock
    private SongsService songsService;

    @InjectMocks
    private SongController songController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostMetadata_Success() {
        MetadataDto metadata = new MetadataDto(
                1,
                "test-title",
                "test-artist",
                "test-album",
                "0:15",
                "2023-03-01"
        );
        IdResponse idResponse = new IdResponse(1);

        when(songsService.addSongMetadata(metadata)).thenReturn(idResponse);

        ResponseEntity<IdResponse> responseEntity = songController.postMetadata(metadata);

        assertEquals(idResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(songsService, times(1)).addSongMetadata(metadata);
    }

    @Test
    void testGetSongMetadataById_Success() {
        int metadataId = 1;
        MetadataDto metadata = new MetadataDto(
                1,
                "test-title",
                "test-artist",
                "test-album",
                "0:15",
                "2023-03-01"
        );

        when(songsService.getSongMetadataById(metadataId)).thenReturn(Optional.of(metadata));

        ResponseEntity<MetadataDto> responseEntity = songController.getSongMetadataById(metadataId);

        assertEquals(metadata, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(songsService, times(1)).getSongMetadataById(metadataId);
    }

    @Test
    void testDeleteSongsByIds_Success() {
        String ids = "1,2,3";
        DeletedIdsResponse deletedIdsResponse = new DeletedIdsResponse(Arrays.asList(1, 2, 3));

        when(songsService.deleteSongsByIds(Arrays.asList(1, 2, 3))).thenReturn(deletedIdsResponse);

        ResponseEntity<DeletedIdsResponse> responseEntity = songController.deleteSongsByIds(ids);

        assertEquals(deletedIdsResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(songsService, times(1)).deleteSongsByIds(Arrays.asList(1, 2, 3));
    }
}
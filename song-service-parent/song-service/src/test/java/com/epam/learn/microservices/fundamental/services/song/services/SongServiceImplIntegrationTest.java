package com.epam.learn.microservices.fundamental.services.song.services;

import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.services.song.SongServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@ActiveProfiles("it")
@SpringBootTest(classes = SongServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SongServiceImplIntegrationTest {

    @Autowired
    private SongsService songsService;

    @Test
    void deleteSongsByListIds() {
        // Given
        final var songInfoDTO1 = new MetadataDto(1, "title-1", "artist-1", "album-1", "0:34", "2023-03-01");
        final var songInfoDTO2 = new MetadataDto(2, "title-2", "artist-2", "album-2", "0:33", "2023-03-01");
        final var idResponse1 = songsService.addSongMetadata(songInfoDTO1);
        final var idResponse2 = songsService.addSongMetadata(songInfoDTO2);
        final var id1 = idResponse1.id();
        final var id2 = idResponse2.id();
        // When
        var songs1 = songsService.getSongMetadataById(id1);
        final var album1 = songs1.orElseThrow().album();
        Assertions.assertEquals("album-1", album1);
        // Then
        final var deletedIdsResponse = songsService.deleteSongsByIds(List.of(id1, id2, 3, 4, 5));
        Assertions.assertEquals(2, deletedIdsResponse.ids().size());
    }
}
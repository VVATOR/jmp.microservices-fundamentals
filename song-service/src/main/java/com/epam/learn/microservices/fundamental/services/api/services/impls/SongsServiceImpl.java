package com.epam.learn.microservices.fundamental.services.api.services.impls;

import com.epam.learn.microservices.fundamental.services.api.models.SongEntity;
import com.epam.learn.microservices.fundamental.services.api.models.projections.IdSongEntityOnlyProjection;
import com.epam.learn.microservices.fundamental.services.api.repositories.SongsRepository;
import com.epam.learn.microservices.fundamental.services.api.services.SongsService;
import com.epam.learn.microservices.fundamental.services.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.services.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.dto.ResourceMetadataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongsServiceImpl implements SongsService {
    private final SongsRepository songRepository;

    @Autowired
    public SongsServiceImpl(SongsRepository songRepository) {
        this.songRepository = songRepository;
    }

    public IdResponse addSongMetadata(ResourceMetadataDto resourceMetadataDto) {
        final var entity = new SongEntity(
                resourceMetadataDto.name(),
                resourceMetadataDto.artist(),
                resourceMetadataDto.album(),
                resourceMetadataDto.length(),
                resourceMetadataDto.resourceId(),
                resourceMetadataDto.year()
        );
        final var songEntity = songRepository.save(entity);
        return new IdResponse(songEntity.getId());
    }

    public Optional<ResourceMetadataDto> getSongMetadataById(Integer id) {
        return songRepository.findById(id)
                .map(songEntity ->
                        new ResourceMetadataDto(
                                songEntity.getName(),
                                songEntity.getArtist(),
                                songEntity.getAlbum(),
                                songEntity.getLength(),
                                songEntity.getResourceId(),
                                songEntity.getYear()
                        )
                );
    }

    public DeletedIdsResponse deleteSongsByIds(List<Integer> id) {
        final var idOnlySongEntityProjections = songRepository.findAllByIdIn(id);
        final var foundSongIds = idOnlySongEntityProjections.stream().map(IdSongEntityOnlyProjection::getId).toList();
        songRepository.deleteAllById(foundSongIds);
        return new DeletedIdsResponse(foundSongIds);
    }
}

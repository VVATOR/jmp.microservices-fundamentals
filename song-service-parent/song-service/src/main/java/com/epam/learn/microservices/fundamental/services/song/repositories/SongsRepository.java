package com.epam.learn.microservices.fundamental.services.song.repositories;

import com.epam.learn.microservices.fundamental.services.song.models.SongEntity;
import com.epam.learn.microservices.fundamental.services.song.models.projections.IdSongEntityOnlyProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongsRepository extends CrudRepository<SongEntity, Integer> {
    List<IdSongEntityOnlyProjection> findAllByIdIn(List<Integer> ids);
}
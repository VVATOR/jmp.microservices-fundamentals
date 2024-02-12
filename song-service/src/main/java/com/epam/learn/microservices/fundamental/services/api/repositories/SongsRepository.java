package com.epam.learn.microservices.fundamental.services.api.repositories;

import com.epam.learn.microservices.fundamental.services.api.models.SongEntity;
import com.epam.learn.microservices.fundamental.services.api.models.projections.IdSongEntityOnlyProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongsRepository extends CrudRepository<SongEntity, Integer> {
    List<IdSongEntityOnlyProjection> findAllByIdIn(List<Integer> ids);
}
package com.epam.learn.microservices.fundamental.services.resources.repositories;

import com.epam.learn.microservices.fundamental.services.resources.models.ResourceEntity;
import com.epam.learn.microservices.fundamental.services.resources.models.projections.IdResourceEntityOnlyProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
    List<IdResourceEntityOnlyProjection> findAllByIdIn(List<Integer> id);
}

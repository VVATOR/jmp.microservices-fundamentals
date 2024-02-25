package com.epam.learn.microservices.fundamental.services.resources.services.impls;

import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.resources.models.ResourceEntity;
import com.epam.learn.microservices.fundamental.services.resources.models.projections.IdResourceEntityOnlyProjection;
import com.epam.learn.microservices.fundamental.services.resources.repositories.ResourceRepository;
import com.epam.learn.microservices.fundamental.services.resources.repositories.StorageService;
import com.epam.learn.microservices.fundamental.services.resources.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourceRepository resourceRepository;
    private final StorageService storageService;

    @Autowired
    public ResourcesServiceImpl(ResourceRepository resourceRepository,
                                StorageService storageService) {
        this.resourceRepository = resourceRepository;
        this.storageService = storageService;
    }

    @Transactional
    public IdResponse addResources(String originalFilename, InputStream mp3InputStream) {
        final var key = UUID.randomUUID() + "_" + originalFilename;
        final var resourceEntity = resourceRepository.save(new ResourceEntity(originalFilename, key));
        final var resourceId = resourceEntity.getId();
        try {
            storageService.saveObject(key, mp3InputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unable to add resource.", e);
        }
        return new IdResponse(resourceId);
    }

    public Optional<ResourceEntity> getResourceBinaryAudioDataById(Integer id) {
        return resourceRepository.findById(id);
    }

    public byte[] getObject(String key) {
        return storageService.getObject(key);
    }


    public DeletedIdsResponse deleteResourceByIds(List<Integer> id) {
        final var idOnlyResourcesEntityProjections = resourceRepository.findAllByIdIn(id);
        final var deletedResourcesIds = idOnlyResourcesEntityProjections.stream().map(IdResourceEntityOnlyProjection::getId).toList();
        resourceRepository.deleteAllById(deletedResourcesIds);
        return new DeletedIdsResponse(deletedResourcesIds);
    }
}
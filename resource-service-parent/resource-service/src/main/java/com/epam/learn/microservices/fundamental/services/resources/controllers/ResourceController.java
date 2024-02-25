package com.epam.learn.microservices.fundamental.services.resources.controllers;


import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.api.ResourceServiceApi;
import com.epam.learn.microservices.fundamental.services.resources.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("")
public class ResourceController implements ResourceServiceApi {
    private static final String IDS_PARAMETER_SEPARATOR = ",";
    private static final int VALID_CSV_LENGTH_RESTRICTION = 200;
    private static final String AUDIO_MPEG_CONTENT_TYPE = "audio/mpeg";
    private final ResourcesService resourcesService;

    @Autowired
    public ResourceController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PostMapping(value = "/resources")
    public ResponseEntity<IdResponse> postResources(@RequestPart("mp3") MultipartFile mp3) throws IOException {
        if (!AUDIO_MPEG_CONTENT_TYPE.equals(mp3.getContentType())) {
            return ResponseEntity.badRequest().build();
        }
        final var idResponse = resourcesService.addResources(mp3.getOriginalFilename(), mp3.getInputStream());
        return ResponseEntity.ok().body(idResponse);
    }


    @GetMapping("/resources/{id}")
    public ResponseEntity<Resource> getResourceFileById(@PathVariable(name = "id") Integer id) {
        final var fileEntity = resourcesService.getResourceBinaryAudioDataById(id);
        if (fileEntity.isPresent()) {
            final var resourceEntity = fileEntity.get();
            final var dataLocation = resourceEntity.getDataLocation();
            final var resourceData = new ByteArrayResource(resourcesService.getObject(dataLocation));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resourceEntity.getOriginalName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resourceData.contentLength())
                    .body(resourceData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/resources")
    public ResponseEntity<DeletedIdsResponse> deleteResourcesByCsvIds(@RequestParam(name = "id") String id) {
        if (id.length() > VALID_CSV_LENGTH_RESTRICTION) {
            return ResponseEntity.internalServerError().build();
        }
        final var integerIds = Arrays
                .stream(id.split(IDS_PARAMETER_SEPARATOR))
                .map(Integer::valueOf)
                .toList();
        final var deletedIdsResponse = resourcesService.deleteResourceByIds(integerIds);
        return ResponseEntity.ok().body(deletedIdsResponse);
    }
}
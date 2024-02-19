package com.epam.learn.microservices.fundamental.services.api;


import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResourceServiceApi {
    ResponseEntity<IdResponse> postResources(MultipartFile mp3) throws IOException;

    ResponseEntity<Resource> getResourceFileById(Integer resourceId);

    ResponseEntity<DeletedIdsResponse> deleteResourcesByCsvIds(String id);
}

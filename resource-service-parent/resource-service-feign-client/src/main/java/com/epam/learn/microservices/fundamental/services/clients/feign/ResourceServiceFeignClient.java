package com.epam.learn.microservices.fundamental.services.clients.feign;

import com.epam.learn.microservices.fundamental.common.dto.DeletedIdsResponse;
import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.services.api.ResourceServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "resource-service", url = "${RESOURCE_SERVICE_URL}")
public interface ResourceServiceFeignClient extends ResourceServiceApi {

    @PostMapping(value = "/resources")
    ResponseEntity<IdResponse> postResources(@RequestPart("mp3") MultipartFile mp3);


    @GetMapping("/resources/{id}")
    ResponseEntity<Resource> getResourceFileById(@PathVariable(name = "id") Integer id);

    @DeleteMapping("/resources")
    ResponseEntity<DeletedIdsResponse> deleteResourcesByCsvIds(@RequestParam(name = "id") String id);
}
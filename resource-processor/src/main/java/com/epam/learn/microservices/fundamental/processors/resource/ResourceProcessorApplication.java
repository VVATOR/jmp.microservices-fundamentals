package com.epam.learn.microservices.fundamental.processors.resource;

import com.epam.learn.microservices.fundamental.processors.resource.services.MetadataExtractor;
import com.epam.learn.microservices.fundamental.services.clients.feign.ResourceServiceFeignClient;
import com.epam.learn.microservices.fundamental.services.clients.feign.SongServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.ByteArrayInputStream;

@Slf4j
@SpringBootApplication
@EnableFeignClients(clients = {ResourceServiceFeignClient.class, SongServiceFeignClient.class})
public class ResourceProcessorApplication implements CommandLineRunner {
    private final MetadataExtractor metadataExtractor;

    private final ResourceServiceFeignClient resourceServiceFeignClient;
    private final SongServiceFeignClient songServiceFeignClient;

    @Autowired
    public ResourceProcessorApplication(final MetadataExtractor metadataExtractor,
                                        final ResourceServiceFeignClient resourceServiceFeignClient,
                                        final SongServiceFeignClient songServiceFeignClient) {
        this.metadataExtractor = metadataExtractor;
        this.resourceServiceFeignClient = resourceServiceFeignClient;
        this.songServiceFeignClient = songServiceFeignClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var responseResource = resourceServiceFeignClient.getResourceFileById(1);
        final var resource = responseResource.getBody();

        final var contentAsByteArray = resource.getContentAsByteArray();
        try (final var fis = new ByteArrayInputStream(contentAsByteArray)) {
            log.info("parse metadata...");
            final var metadataDto = metadataExtractor.prepareMetadataDto(1, fis, "song");
            log.info("{}", metadataDto);
            log.info("Sync call -> song-service : save metadata...");


            final var idResponseResponseEntity = songServiceFeignClient.postMetadata(metadataDto);
            log.info("Sync call -> song-service : save metadata..." + idResponseResponseEntity);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}

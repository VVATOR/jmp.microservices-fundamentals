package com.epam.learn.microservices.fundamental.processors.resource;

import com.epam.learn.microservices.fundamental.common.dto.IdResponse;
import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.processors.resource.services.MetadataExtractor;
import com.epam.learn.microservices.fundamental.services.clients.feign.ResourceServiceFeignClient;
import com.epam.learn.microservices.fundamental.services.clients.feign.SongServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.support.RetryTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootApplication
@EnableFeignClients(clients = {ResourceServiceFeignClient.class, SongServiceFeignClient.class})
public class ResourceProcessorApplication implements CommandLineRunner {
    private final RetryTemplate retryTemplate;
    private final MetadataExtractor metadataExtractor;
    private final ResourceServiceFeignClient resourceServiceFeignClient;
    private final SongServiceFeignClient songServiceFeignClient;

    @Autowired
    public ResourceProcessorApplication(final RetryTemplate retryTemplate,
                                        final MetadataExtractor metadataExtractor,
                                        final ResourceServiceFeignClient resourceServiceFeignClient,
                                        final SongServiceFeignClient songServiceFeignClient) {
        this.retryTemplate = retryTemplate;
        this.metadataExtractor = metadataExtractor;
        this.resourceServiceFeignClient = resourceServiceFeignClient;
        this.songServiceFeignClient = songServiceFeignClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        final var resourceId = 1;
        try {
            log.info("Processing of resource with resourceId={} started.", resourceId);
            final byte[] mp3AsBytes = getSynchronousResourceData(resourceId);

            try (final var bais = new ByteArrayInputStream(mp3AsBytes)) {

                final var metadataDto = metadataExtractor.prepareMetadataDto(resourceId, bais);
                final var idResponseResponseEntity = sendSynchronouslySongMetadata(metadataDto);
                log.info("Metadata of resource with resourceId={} was added to song service with id={}.",resourceId,idResponseResponseEntity.id());

                log.info("Processing of resource with resourceId={} finished successfully.", resourceId);
            }
        } catch (Exception e) {
            log.error("Processing of resource with resourceId={} finished with error! {}", resourceId, e.getMessage());
        }
    }

    private byte[] getSynchronousResourceData(final Integer resourceId) throws IOException {
        return retryTemplate.execute(context -> {
                    log.info("Get resource bytes of resource with resourceId={} (attempt {}).", resourceId, context.getRetryCount());
                    randomIssueThrowToInitiateRetry();
                    return Objects.requireNonNull(resourceServiceFeignClient.getResourceFileById(resourceId)
                            .getBody()).getContentAsByteArray();
                }
        );
    }

    private IdResponse sendSynchronouslySongMetadata(final MetadataDto metadataDto) {
        return retryTemplate.execute(context -> {
                    log.info("Send song metadata of resource with resourceId={} (attempt {}).", metadataDto.resourceId(), context.getRetryCount());
                    randomIssueThrowToInitiateRetry();
                    return songServiceFeignClient
                            .postMetadata(metadataDto)
                            .getBody();
                }
        );
    }

    private static void randomIssueThrowToInitiateRetry() {
        final var isPresentRandomIssueForRetry = ThreadLocalRandom.current().nextBoolean();
        if (isPresentRandomIssueForRetry) {
            throw new RuntimeException("Something happened. Retry!");
        }
    }
}

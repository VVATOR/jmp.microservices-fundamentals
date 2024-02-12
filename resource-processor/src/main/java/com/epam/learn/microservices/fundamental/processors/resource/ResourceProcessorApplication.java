package com.epam.learn.microservices.fundamental.processors.resource;

import com.epam.learn.microservices.fundamental.processors.resource.services.impls.Mp3MetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class ResourceProcessorApplication implements CommandLineRunner {
    private final Mp3MetadataExtractor mp3MetadataExtractor;

    @Autowired
    public ResourceProcessorApplication(Mp3MetadataExtractor mp3MetadataExtractor) {
        this.mp3MetadataExtractor = mp3MetadataExtractor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var filePath = new File("D:\\valhalla_calling.mp3");
        final var fileInputStream = new FileInputStream(filePath);
        final var fileName = filePath.getName();
        mp3MetadataExtractor.prepareMetadataDto(1, fileInputStream, fileName);
    }
}

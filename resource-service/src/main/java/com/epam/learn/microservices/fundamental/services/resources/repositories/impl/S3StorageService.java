package com.epam.learn.microservices.fundamental.services.resources.repositories.impl;

import com.epam.learn.microservices.fundamental.services.resources.repositories.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3StorageService implements StorageService {

    private final S3Client s3Client;

    private final String bucket;

    public S3StorageService(S3Client s3Client, @Value("${aws.s3.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    @Override
    public byte[] getObject(String key) {
        final var request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        return s3Client.getObjectAsBytes(request).asByteArray();
    }

    @Override
    public void saveObject(String key, byte[] data) {
        final var putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
    }
}

package com.epam.learn.microservices.fundamental.services.resources.repositories;

public interface StorageService {

    byte[] getObject(String key);

    void saveObject(String key, byte[] data);
}

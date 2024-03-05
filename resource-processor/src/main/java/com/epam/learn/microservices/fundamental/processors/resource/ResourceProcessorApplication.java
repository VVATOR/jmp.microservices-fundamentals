package com.epam.learn.microservices.fundamental.processors.resource;

import com.epam.learn.microservices.fundamental.services.clients.feign.ResourceServiceFeignClient;
import com.epam.learn.microservices.fundamental.services.clients.feign.SongServiceFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {ResourceServiceFeignClient.class, SongServiceFeignClient.class})
public class ResourceProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessorApplication.class, args);
    }

}

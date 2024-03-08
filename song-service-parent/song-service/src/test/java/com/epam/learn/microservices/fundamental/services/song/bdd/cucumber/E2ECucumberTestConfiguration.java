package com.epam.learn.microservices.fundamental.services.song.bdd.cucumber;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages = "com.epam.learn.microservices.fundamental.services.song")
@EnableJpaRepositories({"com.epam.learn.microservices.fundamental.services.song.repositories"})
@EntityScan({"com.epam.learn.microservices.fundamental.services.song.models"})
@EnableAutoConfiguration
public class E2ECucumberTestConfiguration {
    // We can leave it empty unless otherwise needed
}

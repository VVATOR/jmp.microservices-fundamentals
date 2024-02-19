package com.epam.learn.microservices.fundamental.services.resources.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "resource")
public class ResourceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "original_name")
    private String originalName;

    @Column(name = "data_location")
    private String dataLocation;


    public ResourceEntity() {
        super();
    }

    public ResourceEntity(String originalName, String dataLocation) {
        this.originalName = originalName;
        this.dataLocation = dataLocation;
    }
}
package com.epam.learn.microservices.fundamental.services.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "song")
public class SongEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "artist")
    private String artist;
    @Column(name = "album")
    private String album;
    @Column(name = "length")
    private String length;
    @Column(name = "resource_id")
    private Integer resourceId;
    @Column(name = "year")
    private String year;

    public SongEntity() {
        super();
    }

    public SongEntity(String name, String artist, String album, String length, Integer resourceId, String year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.resourceId = resourceId;
        this.year = year;
    }

}

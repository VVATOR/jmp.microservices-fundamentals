package com.epam.learn.microservices.fundamental.common.dto;

public class MetadataDto {
    private final Integer resourceId;
    private final String name;
    private final String artist;
    private final String album;
    private final String length;
    private final String year;

    public MetadataDto(Integer resourceId, String name, String artist, String album, String length, String year) {
        this.resourceId = resourceId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.year = year;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public Integer resourceId() {
        return resourceId;
    }

    public String name() {
        return name;
    }

    public String artist() {
        return artist;
    }

    public String album() {
        return album;
    }

    public String length() {
        return length;
    }

    public String year() {
        return year;
    }
}
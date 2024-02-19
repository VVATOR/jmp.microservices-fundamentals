package com.epam.learn.microservices.fundamental.processors.resource.services.impls;

import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.processors.resource.exceptions.MetadataPreparationException;
import com.epam.learn.microservices.fundamental.processors.resource.services.MetadataExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class Mp3MetadataExtractor implements MetadataExtractor {
    public static final String ARTIST_METADATA_TAG = "xmpDM:artist";
    public static final String ALBUM_METADATA_TAG = "xmpDM:album";
    public static final String DURATION_METADATA_TAG = "xmpDM:duration";
    public static final String RELEASE_DATE_METADATA_TAG = "xmpDM:releaseDate";

    public Mp3MetadataExtractor() {
        super();
    }

    public MetadataDto prepareMetadataDto(Integer resourceId, InputStream mp3InputStream, String fileName) {
        try {
            final var autoDetectParser = new AutoDetectParser();
            final var handler = new BodyContentHandler();
            final var metadata = new Metadata();
            final var context = new ParseContext();
            autoDetectParser.parse(mp3InputStream, handler, metadata, context);

            final var title = metadata.get(TikaCoreProperties.TITLE);
            final var artist = metadata.get(ARTIST_METADATA_TAG);
            final var album = metadata.get(ALBUM_METADATA_TAG);
            final var length = metadata.get(DURATION_METADATA_TAG);
            final var year = metadata.get(RELEASE_DATE_METADATA_TAG);
            return new MetadataDto(title, artist, album, length, resourceId, year);
        } catch (Exception e) {
            throw new MetadataPreparationException(String.format("Can't get metadata for '%s' file.", fileName), e);
        }
    }
}

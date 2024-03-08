package com.epam.learn.microservices.fundamental.services.song.bdd.cucumber.steps;

import com.epam.learn.microservices.fundamental.common.dto.MetadataDto;
import com.epam.learn.microservices.fundamental.services.song.bdd.cucumber.E2ECucumberTestConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles({"e2e"})
@CucumberContextConfiguration
@SpringBootTest(classes = E2ECucumberTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongControllerCucumberSteps {

    @LocalServerPort
    private int port;

    private final Integer songMetadataId = 1;
    private MetadataDto metadataDto;
    private ResponseEntity<LinkedHashMap> response;

    private HttpEntity<?> getSongDTO() throws JsonProcessingException {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.metadataDto = new MetadataDto(
                songMetadataId,
                "test-title",
                "test-artist",
                "test-album",
                "0:15",
                "2023-03-01"
        );
        final var mapper = new ObjectMapper().writer()
                .withDefaultPrettyPrinter();
        final var songJSONString = mapper.writeValueAsString(metadataDto);
        return new HttpEntity<>(songJSONString, headers);
    }


    @Given("^I sent POST request with valid song metadata in body to /songs$")
    public void whenClientSavesMetadataDto() throws JsonProcessingException {
        final var url = "http://localhost:" + port + "/songs";
        response = new RestTemplate().exchange(url, HttpMethod.POST, getSongDTO(), LinkedHashMap.class);
    }

    @Then("^I got response status code: 200$")
    public void thenResponseStatusCodeIs200() {
        assertThat(response.getStatusCode().value(), equalTo(200));
    }

    @And("^I received saved song metadata id$")
    public void andIReceivedSongMetadataId() {
        var body = response.getBody();
        assertNotNull(body);
        assertThat(body.get("id"), equalTo(songMetadataId));
    }

    @Then("^I able to GET /songs/id$")
    public void thenIGotMetadataDtoById() {
        String url = "http://localhost:" + port + "/songs/" + songMetadataId;
        response = new RestTemplate().exchange(url, HttpMethod.GET, null, LinkedHashMap.class);
    }
}

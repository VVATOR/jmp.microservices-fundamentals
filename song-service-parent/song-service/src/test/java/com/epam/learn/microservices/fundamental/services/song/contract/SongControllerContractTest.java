package com.epam.learn.microservices.fundamental.services.song.contract;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMessageVerifier
@ActiveProfiles("it")
class SongControllerContractTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void validateAddSongMetadataContract() throws Exception {
        mockMvc.perform(post("/songs")
                        .contentType("application/json")
                        .content("""
                                    {
                                        "resourceId": 1,
                                        "name": "Test Song",
                                        "artist": "Test Artist",
                                        "album": "Test Genre",
                                        "length": "1:20",
                                        "year": "2024"
                                    }
                                """))
                .andExpect(status().isOk())
                .andExpect(responseContains("{\"id\":1}"));
    }

    private static ResultMatcher responseContains(String expected) {
        return mvcResult -> {
            String content = mvcResult.getResponse().getContentAsString();
            if (!content.contains(expected)) {
                throw new AssertionError("Response content \"" + content + "\" does not contain \"" + expected + "\"");
            }
        };
    }
}
/*
package com.diginamic.digihello.webRest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VilleResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreationVille() throws Exception {
        String jsonPayload = "{\"nom\":\"Orléans\",\"nbHabitants\":114644,\"nomDepartement\":\"Loiret\",\"codeDepartement\":\"45\"}}";

        mockMvc.perform(post("/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());

        String invalidJsonPayload = "{\"nom\":\"\",\"nbHabitants\":-1,\"nomDepartement\":\"Loiret\",\"codeDepartement\":\"45\"}}";

        mockMvc.perform(post("/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJsonPayload))
                .andExpect(status().isBadRequest());
    }
}*/

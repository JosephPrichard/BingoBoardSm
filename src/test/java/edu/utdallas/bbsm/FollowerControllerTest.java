package edu.utdallas.bbsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.bbsm.bingo.BingoBoardController;
import edu.utdallas.bbsm.follower.Follow;
import edu.utdallas.bbsm.follower.FollowerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FollowerControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper om;

    @BeforeEach
    public void setup() {
        System.out.println("Setup");
        this.om = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new FollowerController()).build();
    }

    @Test
    public void testAccount() throws Exception {
        // create follow
        var follow = new Follow("Joe", "Mama");
        this.mockMvc.perform(post("/follows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(follow)))
            .andExpect(status().isOk());

        // get follow
        this.mockMvc.perform(get("/follows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(follow)))
            .andExpect(status().isOk());

        // delete follow
        this.mockMvc.perform(delete("/follows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(follow)))
            .andExpect(status().isOk());

        // get follow
        this.mockMvc.perform(get("/follows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(follow)))
            .andExpect(status().isOk());
    }
}

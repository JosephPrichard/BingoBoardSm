package edu.utdallas.bbsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.bbsm.bingo.BingoBoardController;
import edu.utdallas.bbsm.bingo.BingoSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BingoBoardControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper om;

    @BeforeEach
    public void setup() {
        System.out.println("Setup");
        this.om = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BingoBoardController()).build();
    }

    @Test
    public void testAccount() throws Exception {
        // get our board
        this.mockMvc.perform(get("/currentBingoBoard")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // fill a square
        var sq = new BingoSquare(2, 1);
        this.mockMvc.perform(post("/fillSquare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(sq)))
            .andExpect(status().isOk());

        // get our board again
        this.mockMvc.perform(get("/currentBingoBoard")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // check if filled
        this.mockMvc.perform(get("/isSubmitted")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(sq)))
            .andExpect(status().isOk());

        // check if our board is completed (no)
        this.mockMvc.perform(get("/isCompleted")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // fill row 3
        for (int i = 0; i < 5; i++) {
            this.mockMvc.perform(post("/fillSquare")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new BingoSquare(2, i))))
                .andExpect(status().isOk());
        }

        // get our board again
        this.mockMvc.perform(get("/currentBingoBoard")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // check if our board is completed
        this.mockMvc.perform(get("/isCompleted")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}

package edu.utdallas.bbsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.bbsm.file.FileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        System.out.println("Setup");
        this.mockMvc = MockMvcBuilders.standaloneSetup(new FileController()).build();
    }

    @Test
    public void test() throws Exception {
        // tests uploading a simple image file, (we don't care about the contents of it)
        MockMultipartFile file = new MockMultipartFile("file", "", "image/jpeg", "qwerqwerq".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/files")
                .file(file))
            .andExpect(status().isOk());
    }
}

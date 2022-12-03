package edu.utdallas.bbsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.bbsm.account.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {
	private MockMvc mockMvc;
	private ObjectMapper om;

	@BeforeEach
	public void setup() {
		System.out.println("Setup");
		this.om = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(new AccountController()).build();
	}

	@Test
	public void test() throws Exception {
		// register the account
		var acc = new AccountDto("Joe", "hello123");
		this.mockMvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(acc)))
			.andExpect(status().isOk());

		// check if we can log in
		this.mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(acc)))
			.andExpect(status().isOk());

		// change the password
		var change = new ChangePasswordDto("Joe", "hello123123", "hello123");
		this.mockMvc.perform(post("/changePassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(change)))
			.andExpect(status().isOk());

		// logout of the account
		var signOut = new SignOutDto("Joe");
		this.mockMvc.perform(post("/signOut")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(signOut)))
			.andExpect(status().isOk());

		// login with the new password
		var acc1 = new AccountDto("Joe", "hello123123");
		this.mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(acc1)))
			.andExpect(status().isOk());
	}

}

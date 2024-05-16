package pandey.intellylabs.backend_assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pandey.intellylabs.backend_assessment.controller.AuthController;
import pandey.intellylabs.backend_assessment.controller.dtos.LoginDTO;
import pandey.intellylabs.backend_assessment.entities.User;
import pandey.intellylabs.backend_assessment.entities.enums.UserType;
import pandey.intellylabs.backend_assessment.global.constants.Const;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);

	@Autowired
	private AuthController auth;
	
	/* Registeration Test Cases */
	@Test
	public void saveUserTest() {
		User user = new User();
		user.setEmail("pandey11@mail.com");
		user.setPassword("3434");
		user.setConfirmPassword("3434");
		// 'Guest','AdminUser','NormalUser'
		user.setUserType(UserType.getEnumByValue("NormalUser"));
		
		ResponseEntity<Object> saveUser = auth.saveUser(user);
		
		Assertions.assertEquals(HttpStatus.CREATED, saveUser.getStatusCode());
	}
	@Test
	void testSuccessfulUserRegistration() {
	    User user = new User();
	    user.setEmail("newuser@example.com");
	    user.setPassword("securePassword");
	    user.setConfirmPassword("securePassword");
	    user.setUserType(UserType.NormalUser);

	    ResponseEntity<Object> response = auth.saveUser(user);

	    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	@Test
	void testDuplicateEmailRegistration() {
	    User existingUser = new User();
	    existingUser.setEmail("existing@example.com");
	    existingUser.setPassword("existingPassword");
	    existingUser.setConfirmPassword("existingPassword");
	    existingUser.setUserType(UserType.NormalUser);
	    
	    ResponseEntity<Object> saveUser = auth.saveUser(existingUser);
		
		Assertions.assertEquals(HttpStatus.CREATED, saveUser.getStatusCode());
	
	    User newUser = new User();
	    newUser.setEmail("existing@example.com");
	    newUser.setPassword("newPassword");
	    newUser.setConfirmPassword("newPassword");
	    newUser.setUserType(UserType.NormalUser);

	    ResponseEntity<Object> response = auth.saveUser(newUser);

	    Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	@Test
	void testInvalidPasswordConfirmation() {
	    User user = new User();
	    user.setEmail("mismatched@example.com");
	    user.setPassword("password1");
	    user.setConfirmPassword("password2"); // Mismatched confirmation password
	    user.setUserType(UserType.NormalUser);

	    ResponseEntity<Object> response = auth.saveUser(user);

	    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	
	/* Login Test Cases*/
	@Test
	void testSuccessfulUserLogin() {
	    LoginDTO loginCred = new LoginDTO();
	    loginCred.setEmail("existing@example.com");
	    loginCred.setPassword("existingPassword");

	    ResponseEntity<Map<String, String>> response = auth.loginUser(loginCred);
	    logger.info("{}", response.getBody());

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(Const.SUCCESS, response.getBody().get(Const.MESSAGE));
	}
	@Test
	void testIncorrectPassword() {
	    LoginDTO loginCred = new LoginDTO();
	    loginCred.setEmail("existing@example.com");
	    loginCred.setPassword("wrongPassword");

	    ResponseEntity<Map<String, String>> response = auth.loginUser(loginCred);

	    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	    assertEquals("Incorrect password!", response.getBody().get(Const.MESSAGE));
	}
	@Test
	void testUserNotFound() {
	    LoginDTO loginCred = new LoginDTO();
	    loginCred.setEmail("nonexistent@example.com");
	    loginCred.setPassword("anyPassword");

	    ResponseEntity<Map<String, String>> response = auth.loginUser(loginCred);

	    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	    assertEquals("User not found!", response.getBody().get(Const.MESSAGE));
	}

	
	
	/*
	@Autowired
	private MockMvc mockMvc;

	// Test case 1: Verify that the endpoint returns HTTP 200 OK
	@Test
	public void testEndpointReturnsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/getAll")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	// Test case 2: Verify a specific response content (e.g., JSON)
	@Test
	public void testEndpointReturnsExpectedContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/your-endpoint"))
				.andExpect(MockMvcResultMatchers.content().json("{\"message\": \"Hello, World!\"}"));
	}

	// Test case 3: Verify an error scenario (e.g., invalid input)
	@Test
	public void testInvalidInputReturnsBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/your-endpoint").param("paramName", "invalidValue"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}*/
}

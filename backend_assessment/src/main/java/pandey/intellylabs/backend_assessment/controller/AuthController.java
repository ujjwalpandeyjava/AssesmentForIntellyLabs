package pandey.intellylabs.backend_assessment.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pandey.intellylabs.backend_assessment.controller.dtos.LoginDTO;
import pandey.intellylabs.backend_assessment.entities.User;
import pandey.intellylabs.backend_assessment.global.constants.Const;
import pandey.intellylabs.backend_assessment.service.inter.AuthServiceInter;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class AuthController {

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthServiceInter authService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginDTO loginCred) {
		logger.info("{}", loginCred);
		return authService.loginUser(loginCred);
	}

	@PostMapping("/register")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		logger.info("saveUser() {}", user);
		return authService.saveUser(user);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> get() {
		logger.debug("Getting all users");
		return ResponseEntity.status(HttpStatus.OK).body(Map.of(Const.Data, authService.getAll()));
	}

}
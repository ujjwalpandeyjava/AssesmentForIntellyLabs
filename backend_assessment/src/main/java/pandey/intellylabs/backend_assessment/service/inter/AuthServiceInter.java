package pandey.intellylabs.backend_assessment.service.inter;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import pandey.intellylabs.backend_assessment.controller.dtos.LoginDTO;
import pandey.intellylabs.backend_assessment.entities.User;

public interface AuthServiceInter {

	ResponseEntity<Object> saveUser(User user);

	ResponseEntity<Map<String, Object>> loginUser(@Valid LoginDTO loginCred);

	Iterable<User> getAll();

}

package pandey.intellylabs.backend_assessment.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pandey.intellylabs.backend_assessment.controller.dtos.LoginDTO;
import pandey.intellylabs.backend_assessment.entities.User;
import pandey.intellylabs.backend_assessment.global.Utlity;
import pandey.intellylabs.backend_assessment.global.constants.Const;
import pandey.intellylabs.backend_assessment.respo.inter.UserRepository;
import pandey.intellylabs.backend_assessment.service.inter.AuthServiceInter;

@Service
public class AuthService implements AuthServiceInter {

	Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private UserRepository userRepo;

	public ResponseEntity<Object> saveUser(@Valid User user) {
		logger.debug("{}", user);

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(Const.MESSAGE, "Must match password"));
		}
		Optional<User> byEmail = userRepo.findByEmail(user.getEmail());
		if (byEmail.isPresent()) {
			String message = user.getEmail() + " already in use!";
			logger.info("While registeration, got duplicate user and responded with: {}", message);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(Const.MESSAGE, message));
		} else {
			logger.info("New user saved with email: {}", user.getEmail());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(Map.of(Const.MESSAGE, Const.SUCCESS, Const.Data, userRepo.save(user)));
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> loginUser(@Valid LoginDTO loginCred) {
		Optional<User> byEmail = userRepo.findByEmail(loginCred.getEmail());
		if (byEmail.isPresent()) {
			if (byEmail.get().getPassword().equals(loginCred.getPassword())) {
				logger.debug("user with email {} logged in at ", loginCred.getEmail(), Instant.now());

				Map<String, Object> resp = Utlity.convertUsingReflection(byEmail.get());
				resp.remove("password");
				resp.remove("confirmPassword");
				resp.put(Const.MESSAGE, Const.SUCCESS);
				resp.put("isLoggedIn", true);

				return ResponseEntity.status(HttpStatus.OK).body(resp);
			} else
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Map.of(Const.MESSAGE, "Incorrect password!"));
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(Const.MESSAGE, "User not found!"));
	}

	@Override
	public Iterable<User> getAll() {
		Iterable<User> all = userRepo.findAll();
		logger.info("Got total {} users", all.spliterator().getExactSizeIfKnown());
		return all;
	}
}

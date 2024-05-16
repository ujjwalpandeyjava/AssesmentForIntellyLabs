package pandey.intellylabs.backend_assessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class _MasterController {
	
	private Logger logger = LoggerFactory.getLogger(_MasterController.class);
	
	@GetMapping(value = { "/", "index", "home" })
	public String getHomeURL() {
		logger.info("Getting home page...");
		return "At simple API";
	}
}

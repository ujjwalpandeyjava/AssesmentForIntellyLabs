package pandey.intellylabs.backend_assessment.global;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utlity {

	static Logger logger = LoggerFactory.getLogger(Utlity.class);

	public static Map<String, Object> convertUsingReflection(Object object) {
		Map<String, Object> map = new HashMap<>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(object));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.info("{}", e.getMessage());
				e.printStackTrace();
			}
		}
		return map;
	}
}

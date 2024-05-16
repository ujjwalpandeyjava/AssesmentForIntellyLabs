package pandey.intellylabs.backend_assessment.entities.enums;

public enum UserType {
	Guest("Guest"), AdminUser("AdminUser"), NormalUser("NormalUser");

	String value;

	UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static UserType getEnumByValue(String enumAsString) {
		UserType[] values = UserType.values();
		for (UserType v : values)
			if (v.getValue().equalsIgnoreCase(enumAsString))
				return v;
		return null;
	}

	public static boolean isValidStatus(String status) {
		return status.equals("");
	}

}
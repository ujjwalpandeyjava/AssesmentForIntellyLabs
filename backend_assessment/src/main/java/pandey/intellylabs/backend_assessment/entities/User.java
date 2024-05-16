package pandey.intellylabs.backend_assessment.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pandey.intellylabs.backend_assessment.entities.enums.UserType;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Must select user type")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@Email
	@NotBlank
	@NotNull
	private String email;
	@NotBlank(message = "Enter password!")
	@NotNull
	private String password;
	@Transient
	@NotNull
	@NotBlank
	private String confirmPassword;
	@NotNull
	private Instant createdDate = Instant.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		setUserType(UserType.getEnumByValue(userType));
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
//		if (getPassword().equals(confirmPassword))
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userType=" + userType + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", createdDate=" + createdDate + "]";
	}

}

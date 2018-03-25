package tokyo.monota.work.result.manager.domain.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

	@NotEmpty
	private String userId;

	@NotEmpty
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

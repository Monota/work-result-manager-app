package tokyo.monota.work.result.manager.core.exception;

public class UserNotLoggedinException extends RuntimeException {

	public UserNotLoggedinException() {
		super("User is not logged in.");
	}
}

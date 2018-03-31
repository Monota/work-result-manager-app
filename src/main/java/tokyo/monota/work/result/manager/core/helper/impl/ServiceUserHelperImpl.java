package tokyo.monota.work.result.manager.core.helper.impl;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import tokyo.monota.work.result.manager.core.exception.UserNotLoggedinException;
import tokyo.monota.work.result.manager.core.helper.ServiceUserHelper;

public class ServiceUserHelperImpl implements ServiceUserHelper {

	public String getCurrentUserId() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if (principal instanceof User) {
			return ((User) principal).getUsername();
		}
		throw new UserNotLoggedinException();
	}
}

package tokyo.monota.work.result.manager.core.helper.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;

import tokyo.monota.work.result.manager.core.exception.UserNotLoggedinException;
import tokyo.monota.work.result.manager.core.helper.ServiceUserHelper;

@Component
public class ServiceUserHelperImpl implements ServiceUserHelper {

	public String getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			if (authentication.getPrincipal() instanceof User) {
				return ((User) (authentication.getPrincipal())).getUsername();
			}
		}
		else if (authentication instanceof SocialAuthenticationToken) {
			SocialAuthenticationToken token = (SocialAuthenticationToken) authentication;
			if (token.getProviderId().equals("google")) {
				return ((SocialUser) (authentication.getPrincipal())).getUsername();
			}
		}
		throw new UserNotLoggedinException();
	}
}

package tokyo.monota.work.result.manager.core.social;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyUserDetailsService implements SocialUserDetailsService {

	@Override
	public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException {
		log.info("access: " + userId);
		return new SocialUser(userId, "", AuthorityUtils.createAuthorityList("user"));
	}
}

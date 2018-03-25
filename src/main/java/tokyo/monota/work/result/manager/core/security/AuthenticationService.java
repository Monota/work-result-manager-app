package tokyo.monota.work.result.manager.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tokyo.monota.work.result.manager.domain.entity.ServiceUserEntity;
import tokyo.monota.work.result.manager.domain.mapper.ServiceUserMapper;

public class AuthenticationService implements UserDetailsService {

	@Autowired
	ServiceUserMapper serviceUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ServiceUserEntity serviceUserEntity = serviceUserMapper.selectServiceUserById(username);

		if (serviceUserEntity == null) {
			throw new UsernameNotFoundException("User " + username + " is not found.");
		}

		User user = new User(
				serviceUserEntity.getUserId(),
				serviceUserEntity.getPassword(),
				AuthorityUtils.createAuthorityList("user"));

		return user;
	}
}

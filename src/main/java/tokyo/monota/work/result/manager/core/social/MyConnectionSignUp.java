package tokyo.monota.work.result.manager.core.social;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class MyConnectionSignUp implements ConnectionSignUp {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String execute(Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(
				"SELECT user_id FROM service_user WHERE user_id = ?", userProfile.getEmail());
		if (resultList.isEmpty()) {
			jdbcTemplate.update("INSERT INTO service_user VALUES (?, ?)", userProfile.getEmail(), null);
		}
		return userProfile.getEmail();
	}
}

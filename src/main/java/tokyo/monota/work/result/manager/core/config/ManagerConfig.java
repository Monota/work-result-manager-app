package tokyo.monota.work.result.manager.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("config.manager")
public class ManagerConfig {

	private String signup;
}

package tokyo.monota.work.result.manager.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.config.boot.GoogleProperties;
import org.springframework.social.google.connect.GoogleConnectionFactory;

@Configuration
@ConditionalOnClass({SocialConfigurerAdapter.class, GoogleConnectionFactory.class})
@ConditionalOnProperty(prefix = "spring.social.google", name = "app-id")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class GoogleAutoConfigurationLocal {

	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(GoogleProperties.class)
	@ConditionalOnWebApplication
	protected static class GoogleConfigurerAdapter extends SocialConfigurerAdapter {

		@Autowired
		private GoogleProperties properties;

		@Bean
		@ConditionalOnMissingBean
		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
		public Google google(final ConnectionRepository repository) {
			final Connection<Google> connection = repository.findPrimaryConnection(Google.class);
			return connection != null ? connection.getApi() : null;
		}

		@Override
		public void addConnectionFactories(final ConnectionFactoryConfigurer configurer, final Environment environment) {
			final GoogleConnectionFactory factory = new GoogleConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
			factory.setScope("email");
			configurer.addConnectionFactory(factory);
		}

		@Override
		public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
			InMemoryUsersConnectionRepository repo = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
			repo.setConnectionSignUp(myConnectionSignUp());
			return repo;
		}

		@Bean
		public MyConnectionSignUp myConnectionSignUp() {
			return new MyConnectionSignUp();
		}
	}
}

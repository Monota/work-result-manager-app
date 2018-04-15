package tokyo.monota.work.result.manager.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

import tokyo.monota.work.result.manager.core.config.ManagerConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ManagerConfig managerConfig;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/common/**", "/auth/**").permitAll()
				.antMatchers("/work/**").hasAnyAuthority("user")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginProcessingUrl("/common/login").loginPage("/common/login")
				.usernameParameter("userId").passwordParameter("password")
				.defaultSuccessUrl("/work/list", true)
				.and()
				.rememberMe()
				.and()
				.apply(new SpringSocialConfigurer()).postLoginUrl("/work/list").signupUrl(managerConfig.getSignup())
				.and()
				.sessionManagement().invalidSessionUrl("/common/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	AuthenticationService authenticationService() {
		return new AuthenticationService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

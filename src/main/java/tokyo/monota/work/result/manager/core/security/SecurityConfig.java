package tokyo.monota.work.result.manager.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/common/**").permitAll()
				.antMatchers("/work/**").hasAnyAuthority("user")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginProcessingUrl("/common/login").loginPage("/common/login")
				.usernameParameter("userId").passwordParameter("password")
				.defaultSuccessUrl("/work/list");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Bean
	AuthenticationService authenticationService() {
		return new AuthenticationService();
	}
}

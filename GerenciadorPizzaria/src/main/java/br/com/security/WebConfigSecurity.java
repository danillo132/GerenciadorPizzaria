package br.com.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.disable().authorizeRequests().anyRequest().authenticated().and().logout().logoutSuccessUrl("/login")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and().addFilterBefore(new LoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new AutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}

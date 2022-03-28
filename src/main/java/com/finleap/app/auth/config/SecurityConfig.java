package com.finleap.app.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 21 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		21 Mar 2022						Initial commit
 * 
 * </pre>
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @formatter:off
	private static final String[] AUTH_WHITELIST = { 
			"/authenticate", 
			"/swagger-resources/**", 
			"/swagger-ui/**",
			"/v3/api-docs/**", 
			"/webjars/**",
			"/actuator/**", 
			"/api-docs/**", 
			"/**/public/**",
			};
	// @formatter:on

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.httpBasic()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.headers().frameOptions().disable()	// Disable frame options in the header
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll() // Disable security for the following url's
		.anyRequest().authenticated() // All other request needs authentication
		.and()
		.csrf().disable();
		// @formatter:on

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

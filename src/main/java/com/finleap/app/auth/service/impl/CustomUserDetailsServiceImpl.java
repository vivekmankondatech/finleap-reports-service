/**
 * 
 */
package com.finleap.app.auth.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finleap.app.auth.config.CustomUserDetails;
import com.finleap.app.auth.service.CustomUserDetailsService;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
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
 * @since 24 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		24 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	private static final String LOAD_USER_BY_USERNAME = "loadUserByUsername";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {

		log.info(CommonConstants.LOG.ENTRY, LOAD_USER_BY_USERNAME, this.getClass().getName());

		Optional<User> user = userRepository.findOneByEmailIdIgnoreCase(username);

		if (user.isPresent()) {

			log.info(CommonConstants.LOG.EXIT, LOAD_USER_BY_USERNAME, this.getClass().getName());

			return new CustomUserDetails(user.get());
		} else {
			log.error(CommonConstants.LOG.ERROR, LOAD_USER_BY_USERNAME, this.getClass().getName());
			throw new UsernameNotFoundException("Access is denied");
		}
	}

	@Override
	public boolean validateUserCredentials(String username, String credentials) {

		Optional<String> password = userRepository.getPasswordByEmailIdIgnoreCase(username);

		return password.isPresent() ? passwordEncoder.matches(credentials, password.get()) : false;

	}

	@Override
	public User getLoggedInUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (Objects.nonNull(authentication)) {

			// Get auth details from the authentication object
			User user = (User) authentication.getPrincipal();

			if (Objects.nonNull(user)) {
				return userRepository.findById(user.getId()).orElseThrow();
			}
		}
		return null;
	}
}

/**
 * 
 */
package com.finleap.app.auth.service.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finleap.app.auth.config.CustomUserDetails;
import com.finleap.app.auth.service.CustomUserDetailsService;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.repository.UserRepository;

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
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {

		Optional<FinleapUser> user = userRepository.findOneByEmailIdIgnoreCase(username);

		if (user.isPresent()) {

			return new CustomUserDetails(user.get());
		} else {
			throw new UsernameNotFoundException("Access is denied");
		}
	}

	@Override
	public boolean validateUserCredentials(String username, String credentials) {

		Optional<FinleapUser> user = userRepository.findOneByEmailIdIgnoreCase(username);

		return user.isPresent() && passwordEncoder.matches(credentials, user.get().getPassword());

	}

	@Override
	public UUID getLoggedInUserId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (Objects.nonNull(authentication)) {

			// Get auth details from the authentication object
			FinleapUser user = (FinleapUser) authentication.getPrincipal();

			if (Objects.nonNull(user)) {
				return user.getId();
			}
		}

		return null;
	}
}

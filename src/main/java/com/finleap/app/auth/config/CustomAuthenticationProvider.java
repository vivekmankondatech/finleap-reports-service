/**
 * 
 */
package com.finleap.app.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.finleap.app.auth.service.CustomUserDetailsService;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.config | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 25 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		25 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String credentials = authentication.getCredentials().toString();

		// Manually authenticating user based on custom checks
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(credentials)
				&& customUserDetailsService.validateUserCredentials(username, credentials)) {
			// Fetch the user details, null check is handled in `loadUserByUsername`
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

			// Check if account is enabled
			if (!userDetails.isEnabled()) {
				throw new DisabledException("Account locked");
			}

			// Check if account is expired
			if (!userDetails.isAccountNonExpired() || !userDetails.isAccountNonLocked()) {
				throw new DisabledException("Account expired or locked");
			}

			// You can add additional security checks here as part of your custom
			// implementation

			// Return the authentication response
			return new UsernamePasswordAuthenticationToken(userDetails, credentials, userDetails.getAuthorities());
		} else {
			throw new BadCredentialsException("Access is denied");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

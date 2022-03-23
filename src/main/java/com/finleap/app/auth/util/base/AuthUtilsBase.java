/**
 * 
 */
package com.finleap.app.auth.util.base;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.app.user.entity.User;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.util.base | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 23 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		23 Mar 2022						Initial commit
 * 
 * </pre>
 */
public class AuthUtilsBase {
	private static final String PRINCIPAL = "principal";

	/**
	 * Get authentication user id form oauth
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected UUID getAuthUserId() {
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return null;
		}
		OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext()
				.getAuthentication();
		if (null != authentication) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			// Get auth details from the authentication object
			Map<String, Object> authDetails = (Map<String, Object>) authentication.getDetails();
			User user = mapper.convertValue(authDetails.get(PRINCIPAL), User.class);

			// Return the user id
			return (null != user.getId()) ? user.getId() : null;
		}
		return null;
	}

	/**
	 * Get basic auth user details from the authentication context <br/>
	 * Note: Not all user details will be present here
	 * 
	 * @param authentication
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected User getBaseAuthDetailsFromAuthContext(Authentication authentication) {
		if (authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}

		if (null != authentication) {
			OAuth2AuthenticationToken oautAuthentication = (OAuth2AuthenticationToken) authentication;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// Get auth details from the authentication object
			Map<String, Object> authDetails = (Map<String, Object>) oautAuthentication.getDetails();
			User user = mapper.convertValue(authDetails.get(PRINCIPAL), User.class);
			// Return the user id
			return (null != user.getId()) ? user : null;
		}
		return null;
	}
}

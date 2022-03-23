/**
 * 
 */
package com.finleap.app.auth.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import com.finleap.app.auth.util.base.AuthUtilsBase;
import com.finleap.app.common.util.CommonUtils;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.repository.UserRepository;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.util | finleap-reports-service
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
//@Component
public class AuthUtils extends AuthUtilsBase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommonUtils commonUtils;

	/**
	 * Get the logged in user details
	 * 
	 * @return
	 */
	public Optional<User> getLoggedInUserDetails() {
		return userRepository.findById(this.getAuthUserId());
	}

	/**
	 * Get the basic user details from the authentication context
	 * 
	 * @param authentication
	 * @return
	 */
	public User getLoggedInUserDetails(Authentication authentication) {
		return this.getBaseAuthDetailsFromAuthContext(authentication);
	}

	/**
	 * Validate if user is disabled
	 * 
	 * @return
	 */
	public boolean isUserDisabled() {
		return getLoggedInUserDetails().isEmpty();
	}

	/**
	 * Validate if the referenced user is disabled
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserDisabled(User user) {
		return user.isDeleted();
	}

	/**
	 * Convert the byte array to {@link OAuth2Authentication}
	 * 
	 * @param tokenBytes
	 * @return
	 */
	public OAuth2AccessToken getOAuth2AccessTokenFromByte(Byte[] tokenBytes) {
		return (OAuth2AccessToken) commonUtils.getObjectFromByte(tokenBytes);
	}

	/**
	 * Get the access token as string from the token bytes
	 * 
	 * @param tokenBytes
	 * @return
	 */
	public String extractTokenFromBytes(Byte[] tokenBytes) {
		OAuth2AccessToken token = commonUtils.getObjectFromByte(tokenBytes);
		return token == null ? null : token.getTokenValue();
	}

}

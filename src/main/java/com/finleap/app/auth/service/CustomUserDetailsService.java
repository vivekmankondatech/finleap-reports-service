/**
 * 
 */
package com.finleap.app.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finleap.app.user.entity.User;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.service | finleap-reports-service
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
public interface CustomUserDetailsService extends UserDetailsService {

	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user
	 */
	User getLoggedInUser();

	/**
	 * Validate user details
	 * 
	 * @param username
	 * @param credentials
	 * @return
	 */
	boolean validateUserCredentials(String username, String credentials);

}

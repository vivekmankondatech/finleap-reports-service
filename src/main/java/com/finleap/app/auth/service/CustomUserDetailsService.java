/**
 * 
 */
package com.finleap.app.auth.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

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
	 * Gets the logged in user ID.
	 *
	 * @return the logged in user ID
	 */
	UUID getLoggedInUserId();

	/**
	 * Validate user details
	 * 
	 * @param username
	 * @param credentials
	 * @return
	 */
	boolean validateUserCredentials(String username, String credentials);

}

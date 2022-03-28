/**
 * 
 */
package com.finleap.app.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.finleap.app.auth.config.CustomUserDetails;
import com.finleap.app.user.entity.FinleapUser;

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
 * @since 27 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		27 Mar 2022						Initial commit
 * 
 * </pre>
 */
public class AbstractSecurityUnitTestConfigurer {

	/**
	 * Get the details of the authenticated user as JWT auth
	 * 
	 * @return
	 */
	protected FinleapUser getAuthUser() {

		// Get the authentication object from the security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Get the user principal
		var user = (User) authentication.getPrincipal();

		// Return the user
		return new CustomUserDetails(user);
	}
}

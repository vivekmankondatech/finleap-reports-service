/**
 * 
 */
package com.finleap.app.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finleap.app.common.util.CommonConstants;
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
public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * @param user
	 * 
	 */
	public CustomUserDetails(User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();
		/**
		 * The roles get appended with 'ROLE_' string when authorities are mapped
		 **/
		if (null != super.getUserRole()) {
			authorities.add(
					new SimpleGrantedAuthority(CommonConstants.Generic.ROLE_DELIMITER + super.getUserRole().getName()));
		}

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 0 - false, 1 - true
		return !super.isAccountLocked();
	}

	@Override
	public boolean isAccountNonLocked() {
		// 0 - false, 1 - true
		return !super.isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 0 - false, 1 - true
		return !super.isCredentialsExpired();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

	/**
	 * Get the username from the User class
	 */
	@Override
	public String getUsername() {
		return super.getEmailId();
	}

}
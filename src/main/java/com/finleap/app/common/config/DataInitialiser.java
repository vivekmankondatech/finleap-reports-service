/**
 * 
 */
package com.finleap.app.common.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.entity.enums.UserRoleType;
import com.finleap.app.user.repository.UserRoleRepository;

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
@Component
public class DataInitialiser {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostConstruct
	public void initUserRoleData() {

		for (UserRoleType type : UserRoleType.values()) {

			if (!userRoleRepository.existsByType(type)) {

				UserRole userRole = new UserRole();
				userRole.setType(type);
				userRoleRepository.save(userRole);
			}
		}

	}
}

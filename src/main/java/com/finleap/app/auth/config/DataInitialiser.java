/**
 * 
 */
package com.finleap.app.auth.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class DataInitialiser {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostConstruct
	public void initUserRoleData() {

		log.info(CommonConstants.LOG.ENTRY, "initUserRoleData", this.getClass().getName());

		List<String> names = new ArrayList<>();

		names.add(CommonConstants.RoleConstants.CUSTOMER);
		names.add(CommonConstants.RoleConstants.ADMIN);

		for (String name : names) {

			if (!userRoleRepository.existsByName(name)) {

				UserRole userRole = new UserRole();
				userRole.setName(name);
				userRoleRepository.save(userRole);
			}
		}

		log.info(CommonConstants.LOG.EXIT, "initUserRoleData", this.getClass().getName());
	}
}

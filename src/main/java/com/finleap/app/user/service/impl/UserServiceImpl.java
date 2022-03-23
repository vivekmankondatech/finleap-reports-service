/**
 * 
 */
package com.finleap.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.repository.UserRepository;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.UserRequestDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.service.impl | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 19 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		19 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());
		return null;
	}

}

/**
 * 
 */
package com.finleap.app.user.mapper;

import org.springframework.stereotype.Component;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.web.dto.response.UserResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.mapper | finleap-reports-service
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
@Component
@Slf4j
public class UserMapper {

	public UserResponseDto toUserResponseDto(User user) {

		log.info(CommonConstants.LOG.ENTRY, "toUserResponseDto", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toUserResponseDto", this.getClass().getName());

		// @formatter:off
		return UserResponseDto.builder()
				.age(user.getAge())
				.emailId(user.getEmailId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.id(user.getId())
				.lastName(user.getLastName())
				.middleName(user.getMiddleName())
				.build();
		// @formatter:on
	}

}

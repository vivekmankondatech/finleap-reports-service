/**
 * 
 */
package com.finleap.app.user.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.web.dto.response.AssigneeResponseDto;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
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

	public List<UserResponseDto> toUserResponseDtos(Collection<User> users) {

		log.info(CommonConstants.LOG.ENTRY, "toUserResponseDtos", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toUserResponseDtos", this.getClass().getName());

		// @formatter:off
		return users.stream()
				.map(this::toUserResponseDto)
				.collect(Collectors.toList());
		// @formatter:on
	}

	public User toUser(UserRequestWithPasswordDto userRequestWithPasswordDto) {

		log.info(CommonConstants.LOG.ENTRY, "toUser", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toUser", this.getClass().getName());

		// @formatter:off
		return User.builder()
				.age(userRequestWithPasswordDto.getAge())
				.emailId(userRequestWithPasswordDto.getEmailId())
				.firstName(userRequestWithPasswordDto.getFirstName())
				.lastName(userRequestWithPasswordDto.getLastName())
				.lastName(userRequestWithPasswordDto.getLastName())
				.middleName(userRequestWithPasswordDto.getMiddleName())
				.password(userRequestWithPasswordDto.getPassword())
				.build();
		// @formatter:on		
	}

	public AssigneeResponseDto toAssignee(User assignee) {

		log.info(CommonConstants.LOG.ENTRY, "toAssignee", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toAssignee", this.getClass().getName());

		// @formatter:off
		return AssigneeResponseDto.builder()
				.age(assignee.getAge())
				.emailId(assignee.getEmailId())
				.firstName(assignee.getFirstName())
				.id(assignee.getId())
				.lastName(assignee.getLastName())
				.middleName(assignee.getMiddleName())
				.build();
		// @formatter:on
	}

}

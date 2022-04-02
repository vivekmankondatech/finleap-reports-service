/**
 * 
 */
package com.finleap.app.user.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.finleap.app.report.web.dto.response.AssigneeResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

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
public class UserMapper {

	public UserResponseDto toUserResponseDto(FinleapUser user) {

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

	public List<UserResponseDto> toUserResponseDtos(Collection<FinleapUser> users) {

		// @formatter:off
		return users.stream()
				.map(this::toUserResponseDto)
				.collect(Collectors.toList());
		// @formatter:on
	}

	public FinleapUser toUser(UserRequestWithPasswordDto userRequestWithPasswordDto) {

		// @formatter:off
		return FinleapUser.builder()
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

	public AssigneeResponseDto toAssignee(FinleapUser assignee) {

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

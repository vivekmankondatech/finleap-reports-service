/**
 * 
 */
package com.finleap.app.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.web.dto.request.UserDeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.request.UserUpdateRequestDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.service | finleap-reports-service
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
public interface UserService {

	/**
	 * Create User
	 * 
	 * @param userRequestWithPasswordDto
	 * @return
	 */
	UserResponseDto createUser(UserRequestWithPasswordDto userRequestWithPasswordDto);

	/**
	 * Modify User
	 * 
	 * @param userUpdateRequestDto
	 * @return
	 */
	UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto);

	/**
	 * Delete User
	 * 
	 * @param userDeleteRequestDto
	 * @return
	 */
	BaseResponseDto deleteUser(UserDeleteRequestDto userDeleteRequestDto);

	/**
	 * Get Logged-in User Details
	 * 
	 * @return
	 */
	UserResponseDto getUser();

	Optional<User> getNewAssigneeByUserIdsNotIn(List<UUID> userIds);

}

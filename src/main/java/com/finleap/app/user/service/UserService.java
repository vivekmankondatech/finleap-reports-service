/**
 * 
 */
package com.finleap.app.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;
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
	 * Create FinleapUser
	 * 
	 * @param userRequestWithPasswordDto
	 * @return
	 */
	UserResponseDto createUser(UserRequestWithPasswordDto userRequestWithPasswordDto);

	/**
	 * Modify FinleapUser
	 * 
	 * @param userUpdateRequestDto
	 * @return
	 */
	UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto);

	/**
	 * Delete FinleapUser
	 * 
	 * @param userDeleteRequestDto
	 * @return
	 */
	BaseResponseDto deleteUser(DeleteRequestDto userDeleteRequestDto);

	/**
	 * Get Logged-in FinleapUser Details
	 * 
	 * @return
	 */
	UserResponseDto getUser();

	/**
	 * Get New Assignee By UserIds Not In
	 * 
	 * @param userIds
	 * @return
	 */
	Optional<FinleapUser> getNewAssigneeByUserIdsNotIn(List<UUID> userIds);

	/**
	 * 
	 * @return
	 */
	Optional<FinleapUser> getUserById(UUID userId);

	/**
	 * Fetch or Fail Logged-in FinleapUser
	 * 
	 * @return
	 */
	FinleapUser fetchOrFailLoggedInUser();

}

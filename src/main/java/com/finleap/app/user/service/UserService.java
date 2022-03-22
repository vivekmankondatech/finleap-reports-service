/**
 * 
 */
package com.finleap.app.user.service;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.user.web.dto.request.UserDeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserModificationRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
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
	 * @param userModificationRequestDto
	 * @return
	 */
	UserResponseDto modifyUser(UserModificationRequestDto userModificationRequestDto);

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

}

/**
 * 
 */
package com.finleap.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finleap.app.auth.util.AuthUtils;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.mapper.UserMapper;
import com.finleap.app.user.repository.UserRepository;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.UserDeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserModificationRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
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

	@Autowired
	private AuthUtils authUtils;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponseDto createUser(UserRequestWithPasswordDto userRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		User user = User.builder().build();

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	@Override
	public UserResponseDto modifyUser(UserModificationRequestDto userModificationRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "modifyUser", this.getClass().getName());

		User user = fetchOrFailLoggedInUser();

		// modify

		log.info(CommonConstants.LOG.EXIT, "modifyUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	@Override
	public BaseResponseDto deleteUser(UserDeleteRequestDto userDeleteRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "deleteUser", this.getClass().getName());

		User user = fetchOrFailLoggedInUser();

		user.setComments(userDeleteRequestDto.getReason());
		user = userRepository.save(user);

		userRepository.delete(user);

		log.info(CommonConstants.LOG.EXIT, "deleteUser", this.getClass().getName());
		return BaseResponseDto.builder().build();
	}

	private User fetchOrFailLoggedInUser() {
		return authUtils.getLoggedInUser();
	}

	@Override
	public UserResponseDto getUser() {

		log.info(CommonConstants.LOG.ENTRY, "getUser", this.getClass().getName());

		User user = fetchOrFailLoggedInUser();

		log.info(CommonConstants.LOG.EXIT, "getUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

}

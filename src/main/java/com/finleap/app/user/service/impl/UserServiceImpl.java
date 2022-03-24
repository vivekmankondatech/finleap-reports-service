/**
 * 
 */
package com.finleap.app.user.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finleap.app.common.exception.DataNotFoundException;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.mapper.UserMapper;
import com.finleap.app.user.repository.UserRepository;
import com.finleap.app.user.repository.UserRoleRepository;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.UserDeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.request.UserUpdateRequestDto;
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
	private UserRoleRepository userRoleRepository;

//	@Autowired
//	private AuthUtils authUtils;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponseDto createUser(UserRequestWithPasswordDto userRequestWithPasswordDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		User user = userMapper.toUser(userRequestWithPasswordDto);
		String name = "";
		user.setUserRole(fetchOrFailUserRoleByName(name));

		user = userRepository.save(user);

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private UserRole fetchOrFailUserRoleByName(String name) {
		return userRoleRepository.findByName(name).orElseThrow(DataNotFoundException::new);

	}

	@Override
	public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "updateUser", this.getClass().getName());

		User user = fetchOrFailLoggedInUser();

		updateUserData(userUpdateRequestDto, user);

		user = userRepository.save(user);

		log.info(CommonConstants.LOG.EXIT, "updateUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	private void updateUserData(UserUpdateRequestDto userUpdateRequestDto, User user) {

		if (Objects.nonNull(userUpdateRequestDto.getAge())) {
			user.setAge(userUpdateRequestDto.getAge());
		}

		if (Objects.nonNull(userUpdateRequestDto.getFirstName())) {
			user.setFirstName(userUpdateRequestDto.getFirstName());
		}

		if (Objects.nonNull(userUpdateRequestDto.getMiddleName())) {
			user.setMiddleName(userUpdateRequestDto.getMiddleName());
		}

		if (Objects.nonNull(userUpdateRequestDto.getLastName())) {
			user.setLastName(userUpdateRequestDto.getLastName());
		}

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

	/**
	 * Fetch or Fail Logged-in User
	 * 
	 * @return
	 */
	private User fetchOrFailLoggedInUser() {
		return new User();
//		return authUtils.getLoggedInUserDetails().orElseThrow(RuntimeException::new);
	}

	@Override
	public UserResponseDto getUser() {

		log.info(CommonConstants.LOG.ENTRY, "getUser", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "getUser", this.getClass().getName());

		return userMapper.toUserResponseDto(fetchOrFailLoggedInUser());
	}

}

/**
 * 
 */
package com.finleap.app.user.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.finleap.app.auth.service.CustomUserDetailsService;
import com.finleap.app.common.exception.DataNotFoundException;
import com.finleap.app.common.exception.NotAcceptableException;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.entity.enums.UserRoleType;
import com.finleap.app.user.mapper.UserMapper;
import com.finleap.app.user.repository.UserRepository;
import com.finleap.app.user.repository.UserRoleRepository;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;
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

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UserMapper userMapper;
	
	

	/**
	 * @param userRepository
	 * @param userRoleRepository
	 * @param customUserDetailsService
	 * @param userMapper
	 */
	public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
			CustomUserDetailsService customUserDetailsService, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.customUserDetailsService = customUserDetailsService;
		this.userMapper = userMapper;
	}

	@Override
	@Transactional
	public UserResponseDto createUser(UserRequestWithPasswordDto userRequestWithPasswordDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		FinleapUser user = userMapper.toUser(userRequestWithPasswordDto);

		user.setUserRole(fetchOrFailUserRoleByType(UserRoleType.EMPLOYEE));

		user = userRepository.save(user);

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private UserRole fetchOrFailUserRoleByType(UserRoleType type) {

		log.info(CommonConstants.LOG.ENTRY, "fetchOrFailUserRoleByType", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "fetchOrFailUserRoleByType", this.getClass().getName());

		return userRoleRepository.findByType(type).orElseThrow(DataNotFoundException::new);

	}

	@Override
	@Transactional
	public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "updateUser", this.getClass().getName());

		FinleapUser user = fetchOrFailLoggedInUser();

		updateUserData(userUpdateRequestDto, user);

		user = userRepository.save(user);

		log.info(CommonConstants.LOG.EXIT, "updateUser", this.getClass().getName());
		return userMapper.toUserResponseDto(user);
	}

	private void updateUserData(UserUpdateRequestDto userUpdateRequestDto, FinleapUser user) {

		boolean isUpdated = false;

		if (Objects.nonNull(userUpdateRequestDto.getAge())) {
			user.setAge(userUpdateRequestDto.getAge());
			isUpdated = true;
		}

		if (StringUtils.isAllBlank(userUpdateRequestDto.getFirstName())) {
			user.setFirstName(userUpdateRequestDto.getFirstName());
			isUpdated = true;
		}

		if (StringUtils.isAllBlank(userUpdateRequestDto.getMiddleName())) {
			user.setMiddleName(userUpdateRequestDto.getMiddleName());
			isUpdated = true;
		}

		if (StringUtils.isAllBlank(userUpdateRequestDto.getLastName())) {
			user.setLastName(userUpdateRequestDto.getLastName());
			isUpdated = true;
		}

		if (StringUtils.isAllBlank(userUpdateRequestDto.getEmailId())) {
			user.setEmailId(userUpdateRequestDto.getEmailId());
			isUpdated = true;
		}

		if (!isUpdated) {
			throw new NotAcceptableException("No Updates were provided.");
		}

	}

	@Override
	@Transactional
	public BaseResponseDto deleteUser(DeleteRequestDto userDeleteRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "deleteUser", this.getClass().getName());

		FinleapUser user = fetchOrFailLoggedInUser();

		user.setComments(userDeleteRequestDto.getReason());
		user = userRepository.save(user);

		userRepository.delete(user);

		log.info(CommonConstants.LOG.EXIT, "deleteUser", this.getClass().getName());
		return BaseResponseDto.builder().build();
	}

	@Override
	public FinleapUser fetchOrFailLoggedInUser() {

		log.info(CommonConstants.LOG.ENTRY, "fetchOrFailLoggedInUser", this.getClass().getName());

		UUID userId = customUserDetailsService.getLoggedInUserId();

		log.info(CommonConstants.LOG.EXIT, "fetchOrFailLoggedInUser", this.getClass().getName());

		return userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
	}

	@Override
	public UserResponseDto getUser() {

		log.info(CommonConstants.LOG.ENTRY, "getUser", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "getUser", this.getClass().getName());

		return userMapper.toUserResponseDto(fetchOrFailLoggedInUser());
	}

	@Override
	public Optional<FinleapUser> getNewAssigneeByUserIdsNotIn(List<UUID> userIds) {

		log.info(CommonConstants.LOG.ENTRY, "getNewAssigneeByUserIdsNotIn", this.getClass().getName());

		List<FinleapUser> users = userRepository.findByIdNotIn(userIds);

		log.info(CommonConstants.LOG.EXIT, "getNewAssigneeByUserIdsNotIn", this.getClass().getName());

		return CollectionUtils.isEmpty(users) ? Optional.empty() : Optional.of(users.get(0));
	}

	@Override
	public Optional<FinleapUser> getUserById(UUID userId) {

		log.info(CommonConstants.LOG.ENTRY, "getUserById", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "getUserById", this.getClass().getName());

		return userRepository.findById(userId);
	}

}

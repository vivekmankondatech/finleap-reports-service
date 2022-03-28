/**
 * 
 */
package com.finleap.app.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.finleap.app.auth.service.CustomUserDetailsService;
import com.finleap.app.common.TestDataProvider;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.entity.enums.UserRoleType;
import com.finleap.app.user.mapper.UserMapper;
import com.finleap.app.user.repository.UserRepository;
import com.finleap.app.user.repository.UserRoleRepository;
import com.finleap.app.user.service.impl.UserServiceImpl;
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
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserRoleRepository userRoleRepository;

	@Mock
	private CustomUserDetailsService customUserDetailsService;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService = new UserServiceImpl(userRepository, userRoleRepository, customUserDetailsService,
			userMapper);

	@BeforeEach
	void setup() {
	}

	@DisplayName("Test - Create User Service")
	@Test
	void createUser() throws Exception {

		// given
		final UserRequestWithPasswordDto userRequestWithPasswordDto = TestDataProvider.getUserRequestWithPasswordDto();

		final FinleapUser user = TestDataProvider.getFinleapUser(UserRoleType.EMPLOYEE);

		final UserRole userRole = user.getUserRole();

		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		// when
		when(userMapper.toUser(userRequestWithPasswordDto)).thenReturn(user);

		when(userRoleRepository.findByType(UserRoleType.EMPLOYEE)).thenReturn(Optional.of(userRole));

		when(userRepository.save(user)).thenReturn(user);

		when(userMapper.toUserResponseDto(user)).thenReturn(userResponseDto);

		// execute
		UserResponseDto expectedResult = userService.createUser(userRequestWithPasswordDto);

		// then
		verify(userMapper, times(1)).toUser(any(UserRequestWithPasswordDto.class));
		verify(userRoleRepository, times(1)).findByType(any(UserRoleType.class));
		verify(userRepository, times(1)).save(any(FinleapUser.class));
		verify(userMapper, times(1)).toUserResponseDto(any(FinleapUser.class));

		verifyNoMoreInteractions(userMapper);
		verifyNoMoreInteractions(userRoleRepository);
		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Update User Service")
	@Test
	void updateUser() throws Exception {

		// given
		final UserUpdateRequestDto userUpdateRequestDto = TestDataProvider.getUserUpdateRequestDto();

		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		// when
		when(customUserDetailsService.getLoggedInUserId()).thenReturn(userId);
		when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
		when(userRepository.save(any(FinleapUser.class))).thenReturn(user);
		when(userMapper.toUserResponseDto(any(FinleapUser.class))).thenReturn(userResponseDto);

		// execute
		UserResponseDto expectedResult = userService.updateUser(userUpdateRequestDto);

		// then
		verify(customUserDetailsService, times(1)).getLoggedInUserId();
		verify(userRepository, times(1)).findById(any(UUID.class));
		verify(userRepository, times(1)).save(any(FinleapUser.class));
		verify(userMapper, times(1)).toUserResponseDto(any(FinleapUser.class));

		verifyNoMoreInteractions(userMapper);
		verifyNoMoreInteractions(customUserDetailsService);
		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Delete User Service")
	@Test
	void deleteUser() throws Exception {

		// given
		final DeleteRequestDto deleteRequestDto = TestDataProvider.getDeleteRequestDto();

		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		// when
		when(customUserDetailsService.getLoggedInUserId()).thenReturn(userId);
		when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
		when(userRepository.save(any(FinleapUser.class))).thenReturn(user);
//		when(userRepository.delete(any(FinleapUser.class))).thenReturn();

		// execute
		BaseResponseDto expectedResult = userService.deleteUser(deleteRequestDto);

		// then
		verify(customUserDetailsService, times(1)).getLoggedInUserId();
		verify(userRepository, times(1)).findById(any(UUID.class));
		verify(userRepository, times(1)).save(any(FinleapUser.class));
		verify(userRepository, times(1)).delete(any(FinleapUser.class));

		verifyNoMoreInteractions(customUserDetailsService);
		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Get User Service")
	@Test
	void getUser() throws Exception {

		// given
		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		// when
		when(customUserDetailsService.getLoggedInUserId()).thenReturn(userId);
		when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
		when(userMapper.toUserResponseDto(any(FinleapUser.class))).thenReturn(userResponseDto);

		// execute
		UserResponseDto expectedResult = userService.getUser();

		// then
		verify(customUserDetailsService, times(1)).getLoggedInUserId();
		verify(userRepository, times(1)).findById(any(UUID.class));
		verify(userMapper, times(1)).toUserResponseDto(any(FinleapUser.class));

		verifyNoMoreInteractions(userMapper);
		verifyNoMoreInteractions(customUserDetailsService);
		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Get New Assignee Excluding given User IDs Service")
	@Test
	void getNewAssigneeByUserIdsNotIn() throws Exception {

		// given
		final UUID userId = TestDataProvider.getRandomUUID();

		final List<UUID> userIds = List.of(userId);

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		// when
		when(userRepository.findByIdNotIn(anyList())).thenReturn(List.of(user));

		// execute
		Optional<FinleapUser> expectedResult = userService.getNewAssigneeByUserIdsNotIn(userIds);

		// then
		verify(userRepository, times(1)).findByIdNotIn(anyList());

		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Get User by ID Service")
	@Test
	void getUserById() throws Exception {

		// given
		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		// when
		when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

		// execute
		Optional<FinleapUser> expectedResult = userService.getUserById(userId);

		// then
		verify(userRepository, times(1)).findById(any(UUID.class));

		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Fetch Or Fail Logged-In User Service")
	@Test
	void fetchOrFailLoggedInUser() throws Exception {

		// given
		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();

		// when
		when(customUserDetailsService.getLoggedInUserId()).thenReturn(userId);
		when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

		// execute
		FinleapUser expectedResult = userService.fetchOrFailLoggedInUser();

		// then
		verify(customUserDetailsService, times(1)).getLoggedInUserId();
		verify(userRepository, times(1)).findById(any(UUID.class));

		verifyNoMoreInteractions(customUserDetailsService);
		verifyNoMoreInteractions(userRepository);

		assertThat(expectedResult).isNotNull();
	}
}

/**
 * 
 */
package com.finleap.app.user.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.app.auth.AbstractSecurityUnitTestConfigurer;
import com.finleap.app.common.TestDataProvider;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.JsonHelperUtil;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.api.UserController;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.request.UserUpdateRequestDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.web | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 20 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		20 Mar 2022						Initial commit
 * 
 * </pre>
 */
@WebMvcTest(UserController.class)
class UserControllerTest extends AbstractSecurityUnitTestConfigurer {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	/* To store the value of the authenticated user */
	protected FinleapUser authUser;

	@BeforeEach
	void setup() {
		// @formatter:off
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
		        .build();
		// @formatter:on

		// Get the user details form the token
		authUser = getAuthUser();
		authUser.setId(UUID.randomUUID());
	}

	@AfterEach
	void tearDown() {

	}

	@DisplayName("Test - Get FinleapUser")
	@WithMockUser(username = "hello@test.com")
	@Test
	void getUser() throws Exception {

		// given
		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		// when
		when(userService.getUser()).thenReturn(userResponseDto);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/users/me")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		UserResponseDto expectedResult = userService.getUser();

		// Check if the findAll method is called at-least once and only once
		verify(userService, times(2)).getUser();
		verifyNoMoreInteractions(userService);

		String expectedResponse = JsonHelperUtil.jsonSerialize(expectedResult, objectMapper);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Create FinleapUser")
	@WithMockUser(username = "hello@test.com")
	@Test
	void createUser() throws Exception {

		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		final UserRequestWithPasswordDto userRequestDto = TestDataProvider.getUserRequestWithPasswordDto();

		// when
		when(userService.createUser(any(UserRequestWithPasswordDto.class))).thenReturn(userResponseDto);

		String content = JsonHelperUtil.jsonSerialize(userRequestDto, objectMapper);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/users/public/new")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on

		// then
		UserResponseDto expectedResult = userService.createUser(userRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(userService, times(2)).createUser(any(UserRequestWithPasswordDto.class));
		verifyNoMoreInteractions(userService);

		String expectedResponse = JsonHelperUtil.jsonSerialize(expectedResult, objectMapper);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Update FinleapUser")
	@WithMockUser(username = "hello@test.com")
	@Test
	void updateUser() throws Exception {

		// given
		final UserUpdateRequestDto userUpdateRequestDto = TestDataProvider.getUserUpdateRequestDto();

		final UserResponseDto userResponseDto = TestDataProvider.getUserResponseDto();

		// when
		when(userService.updateUser(any(UserUpdateRequestDto.class))).thenReturn(userResponseDto);

		String content = JsonHelperUtil.jsonSerialize(userUpdateRequestDto, objectMapper);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		UserResponseDto expectedResult = userService.updateUser(userUpdateRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(userService, times(2)).updateUser(any(UserUpdateRequestDto.class));
		verifyNoMoreInteractions(userService);

		String expectedResponse = JsonHelperUtil.jsonSerialize(expectedResult, objectMapper);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Delete FinleapUser")
	@WithMockUser(username = "hello@test.com")
	@Test
	void deleteUser() throws Exception {

		// given
		final DeleteRequestDto deleteRequestDto = TestDataProvider.getDeleteRequestDto();

		final BaseResponseDto baseResponseDto = TestDataProvider.getBaseResponseDto();

		// when
		when(userService.deleteUser(any(DeleteRequestDto.class))).thenReturn(baseResponseDto);

		String content = JsonHelperUtil.jsonSerialize(deleteRequestDto, objectMapper);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorOccurred").isBoolean())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		BaseResponseDto expectedResult = userService.deleteUser(deleteRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(userService, times(2)).deleteUser(any(DeleteRequestDto.class));
		verifyNoMoreInteractions(userService);

		String expectedResponse = JsonHelperUtil.jsonSerialize(expectedResult, objectMapper);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}
}

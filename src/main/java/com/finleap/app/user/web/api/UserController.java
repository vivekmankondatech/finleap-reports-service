/**
 * 
 */
package com.finleap.app.user.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.UserDeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserModificationRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.web.api | finleap-reports-service
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
@Slf4j
@RestController
@RequestMapping("users")
@Tag(name = "Users", description = "User Service")
public class UserController {

	@Autowired
	private UserService userService;

	// @formatter:off
	@Operation(summary = "Create User", 
			description = "Create User", 
			tags = {"Users" })
	// @formatter:on
	@PostMapping("public/new")
	public ResponseEntity<UserResponseDto> createUser(
			@Valid @RequestBody(required = true) UserRequestWithPasswordDto userRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		UserResponseDto userResponseDto = userService.createUser(userRequestDto);

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Modify User", 
			description = "Modify logged-in User", 
			tags = {"Users" })
	// @formatter:on
	@PatchMapping()
	public ResponseEntity<UserResponseDto> modifyUser(
			@Valid @RequestBody(required = true) UserModificationRequestDto userRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "modifyUser", this.getClass().getName());

		UserResponseDto userResponseDto = userService.modifyUser(userRequestDto);

		log.info(CommonConstants.LOG.EXIT, "modifyUser", this.getClass().getName());

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Get User", 
			description = "Get logged-in User", 
			tags = {"Users" })
	// @formatter:on
	@GetMapping("/me")
	public ResponseEntity<UserResponseDto> getUser() {

		log.info(CommonConstants.LOG.ENTRY, "getUser", this.getClass().getName());

		UserResponseDto userResponseDto = userService.getUser();

		log.info(CommonConstants.LOG.EXIT, "getUser", this.getClass().getName());

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Delete User", 
			description = "Delete logged-in User", 
			tags = {"Users" })
	// @formatter:on
	@DeleteMapping()
	public ResponseEntity<BaseResponseDto> deleteUser(
			@Valid @RequestBody(required = true) UserDeleteRequestDto userDeleteRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "deleteUser", this.getClass().getName());

		BaseResponseDto baseResponseDto = userService.deleteUser(userDeleteRequestDto);

		log.info(CommonConstants.LOG.EXIT, "deleteUser", this.getClass().getName());

		return ResponseEntity.ok(baseResponseDto);
	}

}

/**
 * 
 */
package com.finleap.app.user.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.request.UserUpdateRequestDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@RestController
@RequestMapping("users")
@Tag(name = "Users", description = "FinleapUser Service")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("public/greet")
	public ResponseEntity<String> greet() {
		String response = "The application is up and running.";
		return ResponseEntity.ok(response);
	}

	// @formatter:off
	@Operation(summary = "Create FinleapUser", 
			description = "Create FinleapUser", 
			tags = {"Users" })
	// @formatter:on
	@PostMapping("public/new")
	public ResponseEntity<UserResponseDto> createUser(
			@Valid @RequestBody(required = true) UserRequestWithPasswordDto userRequestDto) {

		UserResponseDto userResponseDto = userService.createUser(userRequestDto);

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Update FinleapUser", 
			description = "Update logged-in FinleapUser", 
			tags = {"Users" },
			security = @SecurityRequirement(name = "finleap-auth"))
	// @formatter:on
	@PutMapping()
	public ResponseEntity<UserResponseDto> updateUser(
			@Valid @RequestBody(required = true) UserUpdateRequestDto userUpdateRequestDto) {

		UserResponseDto userResponseDto = userService.updateUser(userUpdateRequestDto);

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Get FinleapUser", 
			description = "Get logged-in FinleapUser", 
			tags = {"Users" },
			security = @SecurityRequirement(name = "finleap-auth"))
	// @formatter:on
	@GetMapping("/me")
	public ResponseEntity<UserResponseDto> getUser() {

		UserResponseDto userResponseDto = userService.getUser();

		return ResponseEntity.ok(userResponseDto);
	}

	// @formatter:off
	@Operation(summary = "Delete FinleapUser", 
			description = "Delete logged-in FinleapUser", 
			tags = {"Users" },
			security = @SecurityRequirement(name = "finleap-auth"))
	// @formatter:on
	@DeleteMapping()
	public ResponseEntity<BaseResponseDto> deleteUser(
			@Valid @RequestBody(required = true) DeleteRequestDto userDeleteRequestDto) {

		BaseResponseDto baseResponseDto = userService.deleteUser(userDeleteRequestDto);

		return ResponseEntity.ok(baseResponseDto);
	}

}

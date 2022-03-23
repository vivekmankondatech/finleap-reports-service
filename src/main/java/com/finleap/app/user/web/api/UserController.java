/**
 * 
 */
package com.finleap.app.user.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.UserRequestDto;
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
		@Operation(
				summary = "Create Incident Report", 
				description = "Create Incident Report", 
				tags = { "Incident Reports" })
		// @formatter:on
	@PostMapping()
	public ResponseEntity<UserResponseDto> createUser(
			@Valid @RequestBody(required = true) UserRequestDto userRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createUser", this.getClass().getName());

		UserResponseDto userResponseDto = userService.createUser(userRequestDto);

		log.info(CommonConstants.LOG.EXIT, "createUser", this.getClass().getName());

		return ResponseEntity.ok(userResponseDto);
	}
}

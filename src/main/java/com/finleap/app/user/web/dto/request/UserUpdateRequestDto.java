/**
 * 
 */
package com.finleap.app.user.web.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.web.dto.request | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 23 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		23 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserUpdateRequestDto {

	private String firstName;

	private String middleName;

	private String lastName;

	@Min(value = 18, message = "Minimum age must be 18.")
	@Max(value = 150, message = "Valid age must be provided.")
	private Integer age;

	@Email(message = "Valid Email ID must be provided.")
	private String emailId;
}

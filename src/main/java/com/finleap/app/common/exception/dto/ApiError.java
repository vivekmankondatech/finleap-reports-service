/**
 * 
 */
package com.finleap.app.common.exception.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.finleap.app.common.util.DateTimeUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.exception.dto | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 25 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		25 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApiError {

	@Builder.Default
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME)
	private LocalDateTime timestamp = LocalDateTime.now();

	private long status;

	private String error;

	private String message;

	/* Platform specific error codes */
	private String errorCode;

	private String trace;
}

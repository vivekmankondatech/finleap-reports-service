/**
 * 
 */
package com.finleap.app.report.web.dto.request;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.finleap.app.report.entity.enums.IncidentReportStatus;

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
 * com.finleap.app.report.web.dto.request | finleap-reports-service
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class IncidentReportRequestDto {

	@NotEmpty(message = "Title must be provided.")
	private String title;

	@NotNull(message = "Status must be provided.")
	private IncidentReportStatus status;

	@NotNull(message = "AssigneeID must be provided.")
	private UUID assigneeId;

}

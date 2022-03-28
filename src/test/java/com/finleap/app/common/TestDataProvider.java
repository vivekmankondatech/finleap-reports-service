/**
 * 
 */
package com.finleap.app.common;

import java.util.UUID;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.entity.enums.IncidentReportStatus;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.entity.UserRole;
import com.finleap.app.user.entity.enums.UserRoleType;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;
import com.finleap.app.user.web.dto.request.UserRequestWithPasswordDto;
import com.finleap.app.user.web.dto.request.UserUpdateRequestDto;
import com.finleap.app.user.web.dto.response.UserResponseDto;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common | finleap-reports-service
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
public class TestDataProvider {

	private static final int AGE = 21;
	private static final String DELETE_REASON = "No longer required.";
	private static final String REPORT_TITLE = "First Report";
	private static final String MIDDLE_NAME = "new";
	private static final String LAST_NAME = "last";
	private static final String FIRST_NAME = "hello";
	private static final String EMAIL_ID = "hello@test.com";
	private static final String PASSWORD = "test123";

	private TestDataProvider() {
	}

	public static UUID getRandomUUID() {
		return UUID.randomUUID();
	}

	public static UserResponseDto getUserResponseDto() {

		// @formatter:off
		return UserResponseDto.builder()
				.age(21)
				.emailId(EMAIL_ID)
				.firstName(FIRST_NAME)
				.id(getRandomUUID())
				.lastName(LAST_NAME)
				.middleName(MIDDLE_NAME)
				.build();
		// @formatter:on
	}

	public static UserRequestWithPasswordDto getUserRequestWithPasswordDto() {

		// @formatter:off
		return UserRequestWithPasswordDto.builder()
				.age(21)
				.emailId(EMAIL_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.middleName(MIDDLE_NAME)
				.password("test")
				.build();
		// @formatter:on
	}

	public static UserUpdateRequestDto getUserUpdateRequestDto() {

		// @formatter:off
		return UserUpdateRequestDto.builder()
				.age(AGE)
				.emailId(EMAIL_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.middleName(MIDDLE_NAME)
				.build();
		// @formatter:on
	}

	public static IncidentReportResponseDto getIncidentReportResponseDto() {

		// @formatter:off
		return IncidentReportResponseDto.builder()
				.id(getRandomUUID())
				.status(getDefaultIncidentReportStatus())
				.title(REPORT_TITLE)
				.build();
		// @formatter:on
	}

	public static IncidentReportRequestDto getIncidentReportRequestDto() {

		// @formatter:off
		return IncidentReportRequestDto.builder()
				.assigneeId(getRandomUUID())
				.status(getDefaultIncidentReportStatus())
				.title(REPORT_TITLE)
				.build();
		// @formatter:on			
	}

	public static IncidentReportUpdateRequestDto getIncidentReportUpdateRequestDto() {

		// @formatter:off
		return IncidentReportUpdateRequestDto.builder()
				.status(getDefaultIncidentReportStatus())
				.title(REPORT_TITLE + " NEW")
				.build();
		// @formatter:on		
	}

	public static DeleteRequestDto getDeleteRequestDto() {

		// @formatter:off
		return DeleteRequestDto.builder()
				.reason(DELETE_REASON)
				.build();
		// @formatter:on	
	}

	public static BaseResponseDto getBaseResponseDto() {

		// @formatter:off
		return BaseResponseDto.builder()
				.errorCode(null)
				.errorOccurred(false)
				.status(null)
				.build();
		// @formatter:on			
	}

	public static FinleapUser getFinleapUser(UserRoleType userRoleType) {
		// @formatter:off
		return FinleapUser.builder()
				.age(AGE)
				.emailId(EMAIL_ID)
				.enabled(true)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.middleName(MIDDLE_NAME)
				.password(PASSWORD)
				.userRole(getUserRole(userRoleType))
				.build();
		// @formatter:on
	}

	public static FinleapUser getDefaultFinleapUser() {
		// @formatter:off
		return FinleapUser.builder()
				.age(AGE)
				.emailId(EMAIL_ID)
				.enabled(true)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.middleName(MIDDLE_NAME)
				.password(PASSWORD)
				.userRole(getDefaultUserRole())
				.build();
		// @formatter:on
	}

	public static UserRole getUserRole(UserRoleType userRoleType) {
		// @formatter:off
		return UserRole.builder()
				.type(userRoleType)
				.build();
		// @formatter:on
	}

	public static UserRole getDefaultUserRole() {
		// @formatter:off
		return UserRole.builder()
				.type(UserRoleType.EMPLOYEE)
				.build();
		// @formatter:on
	}

	public static IncidentReport getIncidentReport() {

		// @formatter:off
		return IncidentReport.builder()
				.status(getDefaultIncidentReportStatus())
				.title(REPORT_TITLE)
				.build();
		// @formatter:on
	}

	public static IncidentReportStatus getDefaultIncidentReportStatus() {

		return IncidentReportStatus.NEW;
	}

	public static IncidentReportUpdateRequestDto getStatusOnlyIncidentReportUpdateRequestDto(
			IncidentReportStatus incidentReportStatus) {

		// @formatter:off
		return IncidentReportUpdateRequestDto.builder()
				.status(incidentReportStatus)
				.build();
		// @formatter:on		
	}
}

/**
 * 
 */
package com.finleap.app.common;

import java.util.UUID;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.report.entity.enums.IncidentReportStatus;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
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

	private TestDataProvider() {
	}

	public static UUID getRandomItineraryId() {
		return UUID.randomUUID();
	}

	public static UserResponseDto getUserResponseDto() {

		// @formatter:off
		return UserResponseDto.builder()
				.age(21)
				.emailId("hello@test.com")
				.firstName("hello")
				.id(getRandomItineraryId())
				.lastName("last")
				.middleName("new")
				.build();
		// @formatter:on
	}

	public static UserRequestWithPasswordDto getUserRequestWithPasswordDto() {

		// @formatter:off
		return UserRequestWithPasswordDto.builder()
				.age(21)
				.emailId("hello@test.com")
				.firstName("hello")
				.lastName("last")
				.middleName("new")
				.password("test")
				.build();
		// @formatter:on
	}

	public static UserUpdateRequestDto getUserUpdateRequestDto() {

		// @formatter:off
		return UserUpdateRequestDto.builder()
				.age(21)
				.emailId("hello@test.com")
				.firstName("hello")
				.lastName("last")
				.middleName("new")
				.build();
		// @formatter:on
	}

	public static IncidentReportResponseDto getIncidentReportResponseDto() {

		// @formatter:off
		return IncidentReportResponseDto.builder()
				.id(getRandomItineraryId())
				.status(IncidentReportStatus.NEW)
				.title("First Report")
				.build();
		// @formatter:on
	}

	public static IncidentReportRequestDto getIncidentReportRequestDto() {

		// @formatter:off
		return IncidentReportRequestDto.builder()
				.assigneeId(getRandomItineraryId())
				.status(IncidentReportStatus.NEW)
				.title("First Report")
				.build();
		// @formatter:on			
	}

	public static IncidentReportUpdateRequestDto getIncidentReportUpdateRequestDto() {

		// @formatter:off
		return IncidentReportUpdateRequestDto.builder()
				.assigneeId(getRandomItineraryId())
				.status(IncidentReportStatus.ASSIGNED)
				.title("First Report")
				.build();
		// @formatter:on		
	}

	public static DeleteRequestDto getDeleteRequestDto() {

		// @formatter:off
		return DeleteRequestDto.builder()
				.reason("No longer required.")
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
}

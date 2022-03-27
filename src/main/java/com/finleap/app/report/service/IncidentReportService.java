/**
 * 
 */
package com.finleap.app.report.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.service | finleap-reports-service
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
public interface IncidentReportService {

	/**
	 * Create Incident Report
	 * 
	 * @param incidentReportRequestDto
	 * @return
	 */
	IncidentReportResponseDto createIncidentReport(IncidentReportRequestDto incidentReportRequestDto);

	/**
	 * List Existing Incident Reports
	 * 
	 * @param pageable
	 * 
	 * @return
	 */
	List<IncidentReportResponseDto> getAllIncidentReports(Pageable pageable);

	/**
	 * Update an existing incident report
	 * 
	 * @param incidentReportId
	 * @param incidentReportUpdateRequestDto
	 * @return
	 */
	IncidentReportResponseDto updateIncidentReport(UUID incidentReportId,
			IncidentReportUpdateRequestDto incidentReportUpdateRequestDto);

	/**
	 * Delete an existing incident report
	 * 
	 * @param incidentReportId
	 * @param userDeleteRequestDto
	 * @return
	 */
	BaseResponseDto deleteIncidentReport(UUID incidentReportId, DeleteRequestDto userDeleteRequestDto);

}

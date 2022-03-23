/**
 * 
 */
package com.finleap.app.report.service;

import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;

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

}

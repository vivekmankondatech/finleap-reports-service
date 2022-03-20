/**
 * 
 */
package com.finleap.app.report.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.web.api | finleap-reports-service
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
@RequestMapping("reports")
@Tag(name = "Incident Reports", description = "Incident Report Service")
public class IncidentReportController {

	@Autowired
	private IncidentReportService incidentReportService;

	// @formatter:off
		@Operation(
				summary = "Create Incident Report", 
				description = "Create Incident Report", 
				tags = { "Incident Reports" })
		// @formatter:on
	@PostMapping()
	public ResponseEntity<IncidentReportResponseDto> createIncidentReport(
			@Valid @RequestBody(required = true) IncidentReportRequestDto incidentReportRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createIncidentReport", this.getClass().getName());

		IncidentReportResponseDto incidentReportResponseDto = incidentReportService
				.createIncidentReport(incidentReportRequestDto);

		log.info(CommonConstants.LOG.EXIT, "createIncidentReport", this.getClass().getName());

		return ResponseEntity.ok(incidentReportResponseDto);
	}
}

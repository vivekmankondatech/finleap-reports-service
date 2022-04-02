/**
 * 
 */
package com.finleap.app.report.web.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@RestController
@RequestMapping("reports")
@Tag(name = "Incident Reports", description = "Incident Report Service")
@SecurityRequirement(name = "finleap-api")
public class IncidentReportController {

	@Autowired
	private IncidentReportService incidentReportService;

	// @formatter:off
	@Operation(
			summary = "Create Incident Report", 
			description = "Create Incident Report", 
			tags = {"Incident Reports" }
			)
	// @formatter:on
	@PostMapping()
	public ResponseEntity<IncidentReportResponseDto> createIncidentReport(
			@Valid @RequestBody(required = true) IncidentReportRequestDto incidentReportRequestDto) {

		IncidentReportResponseDto incidentReportResponseDto = incidentReportService
				.createIncidentReport(incidentReportRequestDto);

		return ResponseEntity.ok(incidentReportResponseDto);
	}

	// @formatter:off
	@Operation(
			summary = "List existing incident reports", 
			description = "List existing incident reports", 
			tags = {"Incident Reports" }
			)
	// @formatter:on
	@GetMapping()
	public ResponseEntity<Page<IncidentReportResponseDto>> getAllIncidentReports(Pageable pageable) {

		Page<IncidentReportResponseDto> incidentReportResponseDtos = incidentReportService
				.getAllIncidentReports(pageable);

		return ResponseEntity.ok(incidentReportResponseDtos);
	}

	// @formatter:off
	@Operation(
			summary = "Edit an existing incident report", 
			description = "Edit an existing incident report", 
			tags = {"Incident Reports" }
			)
	// @formatter:on
	@PutMapping("{incidentReportId}")
	public ResponseEntity<IncidentReportResponseDto> updateIncidentReport(
			@Valid @PathVariable("incidentReportId") UUID incidentReportId,
			@Valid @RequestBody(required = true) IncidentReportUpdateRequestDto incidentReportUpdateRequestDto) {

		IncidentReportResponseDto incidentReportResponseDto = incidentReportService
				.updateIncidentReport(incidentReportId, incidentReportUpdateRequestDto);

		return ResponseEntity.ok(incidentReportResponseDto);
	}

	// @formatter:off
	@Operation(
			summary = "Delete an existing incident report", 
			description = "Delete an existing incident report", 
			tags = {"Incident Reports" }
			)
	// @formatter:on
	@DeleteMapping("{incidentReportId}")
	public ResponseEntity<BaseResponseDto> deleteIncidentReport(
			@Valid @PathVariable("incidentReportId") UUID incidentReportId,
			@Valid @RequestBody(required = true) DeleteRequestDto deleteRequestDto) {

		BaseResponseDto baseResponseDto = incidentReportService.deleteIncidentReport(incidentReportId,
				deleteRequestDto);

		return ResponseEntity.ok(baseResponseDto);
	}
}

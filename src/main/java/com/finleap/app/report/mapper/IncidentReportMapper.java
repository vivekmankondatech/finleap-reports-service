/**
 * 
 */
package com.finleap.app.report.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.mapper | finleap-reports-service
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
@Component
@Slf4j
public class IncidentReportMapper {

	@Autowired
	private UserMapper userMapper;

	public IncidentReport toIncidentReport(IncidentReportRequestDto incidentReportRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "toIncidentReport", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toIncidentReport", this.getClass().getName());

		// @formatter:off
		return IncidentReport.builder()
				.status(incidentReportRequestDto.getStatus())
				.title(incidentReportRequestDto.getTitle())
				.build();
		// @formatter:on
	}

	public IncidentReportResponseDto toIncidentReportResponseDto(IncidentReport incidentReport) {

		log.info(CommonConstants.LOG.ENTRY, "toIncidentReportResponseDto", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toIncidentReportResponseDto", this.getClass().getName());

		// @formatter:off
		return IncidentReportResponseDto.builder()
				.assignee(userMapper.toAssignee(incidentReport.getAssignee()))
				.status(incidentReport.getStatus())
				.title(incidentReport.getTitle())
				.build();
		// @formatter:on
	}

	public List<IncidentReportResponseDto> toIncidentReportResponseDtos(Collection<IncidentReport> incidentReports) {

		log.info(CommonConstants.LOG.ENTRY, "toIncidentReportResponseDtos", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "toIncidentReportResponseDtos", this.getClass().getName());

		// @formatter:off
		return incidentReports.stream()
				.map(this::toIncidentReportResponseDto)
				.collect(Collectors.toList());
		// @formatter:on

	}

	public Page<IncidentReportResponseDto> toPagedIncidentReportResponseDtos(Page<IncidentReport> incidentReports) {

		log.info(CommonConstants.LOG.ENTRY, "toPagedIncidentReportResponseDtos", this.getClass().getName());
		log.info(CommonConstants.LOG.EXIT, "toPagedIncidentReportResponseDtos", this.getClass().getName());

		return new PageImpl<>(toIncidentReportResponseDtos(incidentReports.getContent()), incidentReports.getPageable(),
				incidentReports.getTotalElements());
	}
}

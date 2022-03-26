/**
 * 
 */
package com.finleap.app.report.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finleap.app.common.exception.DataNotFoundException;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.mapper.IncidentReportMapper;
import com.finleap.app.report.repository.IncidentReportRepository;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.entity.User;
import com.finleap.app.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.service.impl | finleap-reports-service
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
@Service
@Slf4j
public class IncidentReportServiceImpl implements IncidentReportService {

	@Autowired
	private IncidentReportRepository incidentReportRepository;

	@Autowired
	private IncidentReportMapper incidentReportMapper;

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public IncidentReportResponseDto createIncidentReport(IncidentReportRequestDto incidentReportRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createIncidentReport", this.getClass().getName());

		IncidentReport incidentReport = incidentReportMapper.toIncidentReport(incidentReportRequestDto);

		List<UUID> userIds = getExistingAssigneeIds();

		Optional<User> newAssignee = userService.getNewAssigneeByUserIdsNotIn(userIds);

		incidentReport.setAssignee(newAssignee.orElseThrow(DataNotFoundException::new));

		incidentReport = incidentReportRepository.save(incidentReport);

		log.info(CommonConstants.LOG.EXIT, "createIncidentReport", this.getClass().getName());

		return incidentReportMapper.toIncidentReportResponseDto(incidentReport);
	}

	/**
	 * 
	 * @return
	 */
	private List<UUID> getExistingAssigneeIds() {

		log.info(CommonConstants.LOG.ENTRY, "getExistingAssigneeIds", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "getExistingAssigneeIds", this.getClass().getName());

		// @formatter:off
		return incidentReportRepository.findAll()
				.stream()
				.map(user -> user.getAssignee().getId())
				.collect(Collectors.toList());
		// @formatter:on
	}

}

/**
 * 
 */
package com.finleap.app.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.finleap.app.common.exception.DataNotFoundException;
import com.finleap.app.common.exception.InvalidArgumentException;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.mapper.IncidentReportMapper;
import com.finleap.app.report.repository.IncidentReportRepository;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.service.UserService;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;

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

	/**
	 * @param incidentReportRepository
	 * @param incidentReportMapper
	 * @param userService
	 */
	public IncidentReportServiceImpl(IncidentReportRepository incidentReportRepository,
			IncidentReportMapper incidentReportMapper, UserService userService) {
		super();
		this.incidentReportRepository = incidentReportRepository;
		this.incidentReportMapper = incidentReportMapper;
		this.userService = userService;
	}

	@Override
	@Transactional
	public IncidentReportResponseDto createIncidentReport(IncidentReportRequestDto incidentReportRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createIncidentReport", this.getClass().getName());

		IncidentReport incidentReport = incidentReportMapper.toIncidentReport(incidentReportRequestDto);

		List<UUID> userIds = getExistingAssigneeIds();

		Optional<FinleapUser> newAssignee = userService.getNewAssigneeByUserIdsNotIn(userIds);

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

		List<IncidentReport> incidentReports = incidentReportRepository.findAll();

		// @formatter:off
		List<UUID> assigneeIds = incidentReports.stream()
				.map(user -> user.getAssignee().getId())
				.collect(Collectors.toList());
		// @formatter:on

		log.info(CommonConstants.LOG.EXIT, "getExistingAssigneeIds", this.getClass().getName());

		return CollectionUtils.isEmpty(assigneeIds) ? new ArrayList<>() : assigneeIds;
	}

	@Override
	public Page<IncidentReportResponseDto> getAllIncidentReports(Pageable pageable) {

		log.info(CommonConstants.LOG.ENTRY, "getAllIncidentReports", this.getClass().getName());

		Page<IncidentReport> incidentReports = incidentReportRepository.findAll(pageable);

		log.info(CommonConstants.LOG.EXIT, "getAllIncidentReports", this.getClass().getName());

		return incidentReportMapper.toPagedIncidentReportResponseDtos(incidentReports);
	}

	@Override
	public IncidentReportResponseDto updateIncidentReport(UUID incidentReportId,
			IncidentReportUpdateRequestDto incidentReportUpdateRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "updateIncidentReport", this.getClass().getName());

		IncidentReport incidentReport = fetchOrFailIncidentReportById(incidentReportId);

		FinleapUser user = userService.fetchOrFailLoggedInUser();

		boolean isCreator = validatePermissionsToUpdateReport(incidentReport, user);

		if (!isCreator) {
			validateAndUpdateIncidentReportStatus(incidentReport, incidentReportUpdateRequestDto, isCreator);
		} else {
			validateAndUpdateIncidentReport(incidentReport, incidentReportUpdateRequestDto, isCreator);
		}

		incidentReport = incidentReportRepository.save(incidentReport);

		log.info(CommonConstants.LOG.EXIT, "updateIncidentReport", this.getClass().getName());

		return incidentReportMapper.toIncidentReportResponseDto(incidentReport);
	}

	private void validateAndUpdateIncidentReport(IncidentReport incidentReport,
			IncidentReportUpdateRequestDto incidentReportUpdateRequestDto, final boolean isCreator) {

		boolean isUpdated = false;

		if (Objects.nonNull(incidentReportUpdateRequestDto.getAssigneeId()) && !Objects
				.equals(incidentReportUpdateRequestDto.getAssigneeId(), incidentReport.getAssignee().getId())) {

			incidentReport.setAssignee(userService.getUserById(incidentReportUpdateRequestDto.getAssigneeId())
					.orElseThrow(DataNotFoundException::new));

			isUpdated = true;
		}

		if (Objects.nonNull(incidentReportUpdateRequestDto.getTitle())
				&& !Objects.equals(incidentReportUpdateRequestDto.getTitle(), incidentReport.getTitle())) {

			incidentReport.setTitle(incidentReportUpdateRequestDto.getTitle());

			isUpdated = true;
		}

		if (Objects.nonNull(incidentReportUpdateRequestDto.getStatus())
				&& !Objects.equals(incidentReportUpdateRequestDto.getStatus(), incidentReport.getStatus())) {

			incidentReport.setStatus(incidentReportUpdateRequestDto.getStatus());

			isUpdated = true;
		}

		if (!isUpdated) {
			throw new InvalidArgumentException(
					"The Update Request Data for the Incident Report must not be blank or same as the existing data.");
		}

	}

	private void validateAndUpdateIncidentReportStatus(IncidentReport incidentReport,
			IncidentReportUpdateRequestDto incidentReportUpdateRequestDto, final boolean isCreator) {

		if (Objects.nonNull(incidentReportUpdateRequestDto.getAssigneeId())
				|| Objects.nonNull(incidentReportUpdateRequestDto.getTitle())) {

			throw new InvalidArgumentException("The assignee can update the Status only and no other field.");
		}

		if (Objects.isNull(incidentReportUpdateRequestDto.getStatus())) {

			throw new InvalidArgumentException("The assignee must provide a valid status.");

		}

		if (Objects.equals(incidentReportUpdateRequestDto.getStatus(), incidentReport.getStatus())) {

			throw new InvalidArgumentException("The status has already been updated with the current value.");

		}

		incidentReport.setStatus(incidentReportUpdateRequestDto.getStatus());

	}

	/**
	 * @param incidentReport
	 * @param user
	 */
	private boolean validatePermissionsToUpdateReport(IncidentReport incidentReport, FinleapUser user) {
		boolean isCreator;
		if (Objects.equals(incidentReport.getCreatedBy(), user.getId())) {
			isCreator = true;
		} else if (Objects.equals(incidentReport.getAssignee().getId(), user.getId())) {
			isCreator = false;
		} else {
			throw new AccessDeniedException("You do not have sufficient permissions to update Incident Report.");
		}
		return isCreator;
	}

	@Override
	public BaseResponseDto deleteIncidentReport(UUID incidentReportId, DeleteRequestDto deleteRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "deleteIncidentReport", this.getClass().getName());

		IncidentReport incidentReport = fetchOrFailIncidentReportById(incidentReportId);

		incidentReport.setComments(deleteRequestDto.getReason());
		incidentReport = incidentReportRepository.save(incidentReport);

		incidentReportRepository.delete(incidentReport);

		log.info(CommonConstants.LOG.EXIT, "deleteIncidentReport", this.getClass().getName());
		return BaseResponseDto.builder().build();
	}

	/**
	 * 
	 * @param incidentReportId
	 * @return
	 */
	private IncidentReport fetchOrFailIncidentReportById(UUID incidentReportId) {

		log.info(CommonConstants.LOG.ENTRY, "fetchOrFailHikerById", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "fetchOrFailHikerById", this.getClass().getName());

		return incidentReportRepository.findById(incidentReportId).orElseThrow(DataNotFoundException::new);
	}

}

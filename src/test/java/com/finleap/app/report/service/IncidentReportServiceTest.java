/**
 * 
 */
package com.finleap.app.report.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.finleap.app.common.TestDataProvider;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.entity.enums.IncidentReportStatus;
import com.finleap.app.report.mapper.IncidentReportMapper;
import com.finleap.app.report.repository.IncidentReportRepository;
import com.finleap.app.report.service.impl.IncidentReportServiceImpl;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.service.UserService;
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
@ExtendWith(MockitoExtension.class)
class IncidentReportServiceTest {

	@Mock
	private IncidentReportRepository incidentReportRepository;

	@Mock
	private IncidentReportMapper incidentReportMapper;

	@Mock
	private UserService userService;

	@InjectMocks
	private IncidentReportService incidentReportService = new IncidentReportServiceImpl(incidentReportRepository,
			incidentReportMapper, userService);

	@BeforeEach
	void setup() {
	}

	@DisplayName("Test - Create Incident Report Service")
	@Test
	void createIncidentReport() throws Exception {

		// given
		final UUID assigneeId = TestDataProvider.getRandomUUID();

		final FinleapUser assignee = TestDataProvider.getDefaultFinleapUser();
		assignee.setId(assigneeId);

		final UUID existingIncidentReportId = TestDataProvider.getRandomUUID();

		final IncidentReport existingIncidentReport = TestDataProvider.getIncidentReport();
		existingIncidentReport.setId(existingIncidentReportId);
		existingIncidentReport.setAssignee(assignee);

		final UUID newAssigneeId = TestDataProvider.getRandomUUID();

		final FinleapUser newAssignee = TestDataProvider.getDefaultFinleapUser();
		assignee.setId(newAssigneeId);

		final IncidentReportRequestDto incidentReportRequestDto = TestDataProvider.getIncidentReportRequestDto();
		incidentReportRequestDto.setAssigneeId(newAssigneeId);

		final UUID incidentReportId = TestDataProvider.getRandomUUID();

		final IncidentReport incidentReport = TestDataProvider.getIncidentReport();
		incidentReport.setId(incidentReportId);
		incidentReport.setAssignee(newAssignee);

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		// when
		when(incidentReportMapper.toIncidentReport(incidentReportRequestDto)).thenReturn(incidentReport);
		when(incidentReportRepository.findAll()).thenReturn(List.of(existingIncidentReport));
		when(userService.getNewAssigneeByUserIdsNotIn(anyList())).thenReturn(Optional.of(newAssignee));
		when(incidentReportRepository.save(incidentReport)).thenReturn(incidentReport);
		when(incidentReportMapper.toIncidentReportResponseDto(incidentReport)).thenReturn(incidentReportResponseDto);

		// execute
		IncidentReportResponseDto expectedResult = incidentReportService.createIncidentReport(incidentReportRequestDto);

		// then
		verify(incidentReportMapper, times(1)).toIncidentReport(any(IncidentReportRequestDto.class));
		verify(incidentReportRepository, times(1)).findAll();
		verify(userService, times(1)).getNewAssigneeByUserIdsNotIn(anyList());
		verify(incidentReportRepository, times(1)).save(any(IncidentReport.class));
		verify(incidentReportMapper, times(1)).toIncidentReportResponseDto(any(IncidentReport.class));

		verifyNoMoreInteractions(incidentReportMapper);
		verifyNoMoreInteractions(incidentReportRepository);
		verifyNoMoreInteractions(userService);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Get All Incident Reports Service")
	@Test
	void getAllIncidentReports() throws Exception {

		// given
		final PageRequest pageRequest = PageRequest.of(0, 20);

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		final UUID incidentReportId = TestDataProvider.getRandomUUID();

		final IncidentReport incidentReport = TestDataProvider.getIncidentReport();
		incidentReport.setId(incidentReportId);

		final Page<IncidentReportResponseDto> page = new PageImpl<>(List.of(incidentReportResponseDto));

		final Page<IncidentReport> incidentReports = new PageImpl<>(List.of(incidentReport));

		// when
		when(incidentReportRepository.findAll(pageRequest)).thenReturn(incidentReports);
		when(incidentReportMapper.toPagedIncidentReportResponseDtos(incidentReports)).thenReturn(page);

		// execute
		Page<IncidentReportResponseDto> expectedResult = incidentReportService.getAllIncidentReports(pageRequest);

		// then
		verify(incidentReportRepository, times(1)).findAll(pageRequest);
		verify(incidentReportMapper, times(1)).toPagedIncidentReportResponseDtos(incidentReports);

		verifyNoMoreInteractions(incidentReportRepository);
		verifyNoMoreInteractions(incidentReportMapper);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test 1 - Update Incident Report Service By Assignee")
	@Test
	void updateIncidentReportByAssignee() throws Exception {

		// given
		final UUID assigneeId = TestDataProvider.getRandomUUID();

		final FinleapUser assignee = TestDataProvider.getDefaultFinleapUser();
		assignee.setId(assigneeId);

		final IncidentReportUpdateRequestDto incidentReportUpdateRequestDto = TestDataProvider
				.getStatusOnlyIncidentReportUpdateRequestDto(IncidentReportStatus.CLOSED);

		final UUID incidentReportId = TestDataProvider.getRandomUUID();

		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();
		user.setId(userId);

		final IncidentReport incidentReport = TestDataProvider.getIncidentReport();
		incidentReport.setId(incidentReportId);
		incidentReport.setAssignee(assignee);
		incidentReport.setCreatedBy(userId);

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		// when
		when(incidentReportRepository.findById(incidentReportId)).thenReturn(Optional.of(incidentReport));
		when(incidentReportRepository.save(incidentReport)).thenReturn(incidentReport);
		when(userService.fetchOrFailLoggedInUser()).thenReturn(user);
		when(incidentReportMapper.toIncidentReportResponseDto(incidentReport)).thenReturn(incidentReportResponseDto);

		// execute
		IncidentReportResponseDto expectedResult = incidentReportService.updateIncidentReport(incidentReportId,
				incidentReportUpdateRequestDto);

		// then
		verify(userService, times(1)).fetchOrFailLoggedInUser();
		verify(incidentReportRepository, times(1)).findById(any(UUID.class));
		verify(incidentReportRepository, times(1)).save(any(IncidentReport.class));
		verify(incidentReportMapper, times(1)).toIncidentReportResponseDto(incidentReport);

		verifyNoMoreInteractions(userService);
		verifyNoMoreInteractions(incidentReportRepository);
		verifyNoMoreInteractions(incidentReportMapper);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test 2 - Update Incident Report Service By Creator")
	@Test
	void updateIncidentReportByCreator() throws Exception {

		// given
		final UUID assigneeId = TestDataProvider.getRandomUUID();

		final FinleapUser assignee = TestDataProvider.getDefaultFinleapUser();
		assignee.setId(assigneeId);

		final IncidentReportUpdateRequestDto incidentReportUpdateRequestDto = TestDataProvider
				.getIncidentReportUpdateRequestDto();

		final UUID incidentReportId = TestDataProvider.getRandomUUID();

		final UUID userId = TestDataProvider.getRandomUUID();

		final FinleapUser user = TestDataProvider.getDefaultFinleapUser();
		user.setId(userId);

		final IncidentReport incidentReport = TestDataProvider.getIncidentReport();
		incidentReport.setId(incidentReportId);
		incidentReport.setAssignee(assignee);
		incidentReport.setCreatedBy(userId);

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		// when
		when(incidentReportRepository.findById(incidentReportId)).thenReturn(Optional.of(incidentReport));
		when(incidentReportRepository.save(incidentReport)).thenReturn(incidentReport);
		when(userService.fetchOrFailLoggedInUser()).thenReturn(user);
		when(incidentReportMapper.toIncidentReportResponseDto(incidentReport)).thenReturn(incidentReportResponseDto);

		// execute
		IncidentReportResponseDto expectedResult = incidentReportService.updateIncidentReport(incidentReportId,
				incidentReportUpdateRequestDto);

		// then
		verify(userService, times(1)).fetchOrFailLoggedInUser();
		verify(incidentReportRepository, times(1)).findById(any(UUID.class));
		verify(incidentReportRepository, times(1)).save(any(IncidentReport.class));
		verify(incidentReportMapper, times(1)).toIncidentReportResponseDto(incidentReport);

		verifyNoMoreInteractions(userService);
		verifyNoMoreInteractions(incidentReportRepository);
		verifyNoMoreInteractions(incidentReportMapper);

		assertThat(expectedResult).isNotNull();
	}

	@DisplayName("Test - Delete Incident Report Service")
	@Test
	void deleteIncidentReport() throws Exception {

		// given
		final UUID incidentReportId = TestDataProvider.getRandomUUID();

		final IncidentReport incidentReport = TestDataProvider.getIncidentReport();
		incidentReport.setId(incidentReportId);

		final DeleteRequestDto deleteRequestDto = TestDataProvider.getDeleteRequestDto();

		// when
		when(incidentReportRepository.findById(incidentReportId)).thenReturn(Optional.of(incidentReport));
		when(incidentReportRepository.save(incidentReport)).thenReturn(incidentReport);

		// execute
		BaseResponseDto expectedResult = incidentReportService.deleteIncidentReport(incidentReportId, deleteRequestDto);

		// then
		verify(incidentReportRepository, times(1)).findById(any(UUID.class));
		verify(incidentReportRepository, times(1)).save(any(IncidentReport.class));
		verify(incidentReportRepository, times(1)).delete(any(IncidentReport.class));

		verifyNoMoreInteractions(incidentReportRepository);

		assertThat(expectedResult).isNotNull();
	}

}

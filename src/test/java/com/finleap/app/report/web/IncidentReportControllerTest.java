/**
 * 
 */
package com.finleap.app.report.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.app.auth.AbstractSecurityUnitTestConfigurer;
import com.finleap.app.common.TestDataProvider;
import com.finleap.app.common.response.dto.BaseResponseDto;
import com.finleap.app.common.util.JsonHelperUtil;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.api.IncidentReportController;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.request.IncidentReportUpdateRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;
import com.finleap.app.user.entity.FinleapUser;
import com.finleap.app.user.web.dto.request.DeleteRequestDto;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.web | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 20 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		20 Mar 2022						Initial commit
 * 
 * </pre>
 */
@WebMvcTest(IncidentReportController.class)
class IncidentReportControllerTest extends AbstractSecurityUnitTestConfigurer {

	@MockBean
	private IncidentReportService incidentReportService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	/* To store the value of the authenticated user */
	protected FinleapUser authUser;

	@BeforeEach
	void setup() {
		// @formatter:off
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
		        .build();
		// @formatter:on

		// Get the user details form the token
		authUser = getAuthUser();
		authUser.setId(UUID.randomUUID());
	}

	@AfterEach
	void tearDown() {

	}

	@DisplayName("Test - Get All Incident Reports")
	@WithMockUser(username = "hello@test.com")
	@Test
	void getAllIncidentReports() throws Exception {

		// given
		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		Pageable pageable = PageRequest.of(0, 20);

		final Page<IncidentReportResponseDto> page = new PageImpl<>(Arrays.asList(incidentReportResponseDto));

		// when
		when(incidentReportService.getAllIncidentReports(pageable)).thenReturn(page);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/reports")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empty").isBoolean())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		Page<IncidentReportResponseDto> expectedResult = incidentReportService.getAllIncidentReports(pageable);

		// Check if the findAll method is called at-least once and only once
		verify(incidentReportService, times(2)).getAllIncidentReports(pageable);
		verifyNoMoreInteractions(incidentReportService);

		String expectedResponse = objectMapper.writeValueAsString(expectedResult);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Create Incident Report")
	@WithMockUser(username = "hello@test.com")
	@Test
	void createIncidentReport() throws Exception {

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		final IncidentReportRequestDto incidentReportRequestDto = TestDataProvider.getIncidentReportRequestDto();

		// when
		when(incidentReportService.createIncidentReport(any(IncidentReportRequestDto.class)))
				.thenReturn(incidentReportResponseDto);

		String content = JsonHelperUtil.jsonSerialize(incidentReportRequestDto);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/reports")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on

		// then
		IncidentReportResponseDto expectedResult = incidentReportService.createIncidentReport(incidentReportRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(incidentReportService, times(2)).createIncidentReport(any(IncidentReportRequestDto.class));
		verifyNoMoreInteractions(incidentReportService);

		String expectedResponse = objectMapper.writeValueAsString(expectedResult);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Update Incident Report")
	@WithMockUser(username = "hello@test.com")
	@Test
	void updateIncidentReport() throws Exception {

		// given
		final IncidentReportUpdateRequestDto incidentReportUpdateRequestDto = TestDataProvider
				.getIncidentReportUpdateRequestDto();

		final UUID incidentReportId = TestDataProvider.getRandomItineraryId();

		final IncidentReportResponseDto incidentReportResponseDto = TestDataProvider.getIncidentReportResponseDto();

		// when
		when(incidentReportService.updateIncidentReport(any(UUID.class), any(IncidentReportUpdateRequestDto.class)))
				.thenReturn(incidentReportResponseDto);

		String content = JsonHelperUtil.jsonSerialize(incidentReportUpdateRequestDto);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/reports/{incidentReportId}",  incidentReportId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		IncidentReportResponseDto expectedResult = incidentReportService.updateIncidentReport(incidentReportId,
				incidentReportUpdateRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(incidentReportService, times(2)).updateIncidentReport(any(UUID.class),
				any(IncidentReportUpdateRequestDto.class));
		verifyNoMoreInteractions(incidentReportService);

		String expectedResponse = objectMapper.writeValueAsString(expectedResult);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

	@DisplayName("Test - Delete Incident Report")
	@WithMockUser(username = "hello@test.com")
	@Test
	void deleteIncidentReport() throws Exception {

		// given
		final DeleteRequestDto deleteRequestDto = TestDataProvider.getDeleteRequestDto();

		final UUID incidentReportId = TestDataProvider.getRandomItineraryId();

		final BaseResponseDto baseResponseDto = TestDataProvider.getBaseResponseDto();

		// when
		when(incidentReportService.deleteIncidentReport(any(UUID.class), any(DeleteRequestDto.class)))
				.thenReturn(baseResponseDto);

		String content = JsonHelperUtil.jsonSerialize(deleteRequestDto);

		// @formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/reports/{incidentReportId}",  incidentReportId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorOccurred").isBoolean())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		// @formatter:on	

		BaseResponseDto expectedResult = incidentReportService.deleteIncidentReport(incidentReportId, deleteRequestDto);

		// Check if the findAll method is called at-least once and only once
		verify(incidentReportService, times(2)).deleteIncidentReport(any(UUID.class), any(DeleteRequestDto.class));
		verifyNoMoreInteractions(incidentReportService);

		String expectedResponse = objectMapper.writeValueAsString(expectedResult);

		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}
}

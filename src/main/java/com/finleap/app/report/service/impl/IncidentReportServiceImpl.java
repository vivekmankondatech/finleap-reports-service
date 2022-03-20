/**
 * 
 */
package com.finleap.app.report.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finleap.app.common.util.CommonConstants;
import com.finleap.app.report.repository.IncidentReportRepository;
import com.finleap.app.report.service.IncidentReportService;
import com.finleap.app.report.web.dto.request.IncidentReportRequestDto;
import com.finleap.app.report.web.dto.response.IncidentReportResponseDto;

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

	@Override
	public IncidentReportResponseDto createIncidentReport(IncidentReportRequestDto incidentReportRequestDto) {

		log.info(CommonConstants.LOG.ENTRY, "createIncidentReport", this.getClass().getName());

		log.info(CommonConstants.LOG.EXIT, "createIncidentReport", this.getClass().getName());
		return null;
	}

}

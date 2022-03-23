/**
 * 
 */
package com.finleap.app.report.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.finleap.app.report.entity.enums.IncidentReportStatus;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.converter | finleap-reports-service
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
@Converter
public class IncidentReportStatusConverter implements AttributeConverter<IncidentReportStatus, Long> {

	@Override
	public Long convertToDatabaseColumn(IncidentReportStatus attribute) {

		if (attribute != null) {
			return attribute.getCode();
		}
		return null;
	}

	@Override
	public IncidentReportStatus convertToEntityAttribute(Long dbData) {

		if (dbData != null) {
			return IncidentReportStatus.getInstanceFromCode(dbData);
		}
		return null;
	}

}

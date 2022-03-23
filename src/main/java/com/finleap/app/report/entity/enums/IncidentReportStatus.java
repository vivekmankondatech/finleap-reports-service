/**
 * 
 */
package com.finleap.app.report.entity.enums;

import java.util.HashMap;

import com.finleap.app.common.enums.base.BaseEnum;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.entity.enums | finleap-reports-service
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
public enum IncidentReportStatus implements BaseEnum {

	// @formatter:off
	NEW(1001, "NEW"),
	ASSIGNED(1002, "ASSIGNED"),
	CLOSED(1003, "CLOSED"),
    ;
    // @formatter:on

	private Long code;

	private String displayName;

	private static HashMap<Long, IncidentReportStatus> enumMap = new HashMap<>(IncidentReportStatus.values().length);

	private static HashMap<String, IncidentReportStatus> enumStringMap = new HashMap<>(
			IncidentReportStatus.values().length);

	static {
		for (IncidentReportStatus status : IncidentReportStatus.values()) {
			enumMap.put(status.code, status);
			enumStringMap.put(status.displayName, status);
		}
	}

	IncidentReportStatus(long code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public static IncidentReportStatus getInstanceFromCode(long code) {
		return enumMap.get(code);
	}

	public static IncidentReportStatus getInstanceFromString(String name) {
		return enumStringMap.get(name.toLowerCase());
	}

	@Override
	public long getClassification() {
		return 1000;
	}

	@Override
	public Long getCode() {
		return code;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

}

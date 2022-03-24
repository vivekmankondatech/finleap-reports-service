/**
 * 
 */
package com.finleap.app.user.entity.enums;

import java.util.HashMap;

import com.finleap.app.common.enums.base.BaseEnum;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.entity.enums | finleap-reports-service
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
public enum UserRoleType implements BaseEnum {

	// @formatter:off
	ADMIN(2001, "ADMIN"),
	EMPLOYEE(2002, "EMPLOYEE"),
    ;
    // @formatter:on

	private Long code;

	private String displayName;

	private static HashMap<Long, UserRoleType> enumMap = new HashMap<>(UserRoleType.values().length);

	private static HashMap<String, UserRoleType> enumStringMap = new HashMap<>(UserRoleType.values().length);

	static {
		for (UserRoleType userRoleType : UserRoleType.values()) {
			enumMap.put(userRoleType.code, userRoleType);
			enumStringMap.put(userRoleType.displayName, userRoleType);
		}
	}

	UserRoleType(long code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public static UserRoleType getInstanceFromCode(long code) {
		return enumMap.get(code);
	}

	public static UserRoleType getInstanceFromString(String name) {
		return enumStringMap.get(name.toLowerCase());
	}

	@Override
	public long getClassification() {
		return 2000;
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
/**
 * 
 */
package com.finleap.app.user.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.finleap.app.user.entity.enums.UserRoleType;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.converter | finleap-reports-service
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
@Converter
public class UserRoleTypeConverter implements AttributeConverter<UserRoleType, Long> {

	@Override
	public Long convertToDatabaseColumn(UserRoleType attribute) {

		if (attribute != null) {
			return attribute.getCode();
		}
		return null;
	}

	@Override
	public UserRoleType convertToEntityAttribute(Long dbData) {

		if (dbData != null) {
			return UserRoleType.getInstanceFromCode(dbData);
		}
		return null;
	}

}

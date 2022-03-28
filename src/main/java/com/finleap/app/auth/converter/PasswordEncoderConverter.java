/**
 * 
 */
package com.finleap.app.auth.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.converter | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 24 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		24 Mar 2022						Initial commit
 * 
 * </pre>
 */
@Converter
public class PasswordEncoderConverter implements AttributeConverter<String, String> {

	BCryptPasswordEncoder passwordEncoder;

	@Override
	public String convertToDatabaseColumn(String rawPassword) {
		passwordEncoder = new BCryptPasswordEncoder();
		if (rawPassword != null)
			return passwordEncoder.encode(rawPassword);
		return null;
	}

	@Override
	public String convertToEntityAttribute(String password) {
		return password;
	}

}

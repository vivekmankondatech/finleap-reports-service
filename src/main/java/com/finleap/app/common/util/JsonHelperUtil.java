/**
 * 
 */
package com.finleap.app.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Any License information can go here
 */

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.util | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 23 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		23 Mar 2022						Initial commit
 * 
 * </pre>
 */
public class JsonHelperUtil {

	private JsonHelperUtil() {
		throw new IllegalStateException("Utility class cannot be instantiated.");
	}

	/**
	 * Convert JSON object to JSON String
	 * 
	 * @param instance
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String jsonSerialize(Object instance, ObjectMapper mapper) throws JsonProcessingException {

		return mapper.writeValueAsString(instance);
	}
}

/**
 * 
 */
package com.finleap.app.common.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class JsonHelperUtil {

	@Autowired
	private static ObjectMapper mapper;

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

		log.info(CommonConstants.LOG.ENTRY, "jsonSerialize", JsonHelperUtil.class.getSimpleName());

		log.info(CommonConstants.LOG.EXIT, "jsonSerialize", JsonHelperUtil.class.getSimpleName());

		return mapper.writeValueAsString(instance);
	}
}

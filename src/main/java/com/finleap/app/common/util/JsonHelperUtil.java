/**
 * 
 */
package com.finleap.app.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

	private JsonHelperUtil() {
		throw new IllegalStateException("Utility class cannot be instantiated.");
	}

	/**
	 * Get the instance of the mapper
	 * 
	 * @return
	 */
	public static ObjectMapper getMapper() {

		log.info(CommonConstants.LOG.ENTRY, "getMapper", JsonHelperUtil.class.getSimpleName());

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		log.info(CommonConstants.LOG.EXIT, "getMapper", JsonHelperUtil.class.getSimpleName());

		return mapper;
	}

	/**
	 * Convert JSON object to JSON String
	 * 
	 * @param instance
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String jsonSerialize(Object instance) throws JsonProcessingException {
		ObjectMapper mapper = getMapper();

		// For representing time as timestamps
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return mapper.writeValueAsString(instance);
	}
}

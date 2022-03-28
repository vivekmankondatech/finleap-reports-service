/**
 * 
 */
package com.finleap.app.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
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
	 * Get the list of objects from the string data
	 * 
	 * @param <T>
	 * @param data
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> getListOfObjects(String data, Class<T> clazz) throws IOException {

		log.info(CommonConstants.LOG.ENTRY, "getListOfObjects", JsonHelperUtil.class.getSimpleName());

		ObjectMapper mapper = getMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);

		log.info(CommonConstants.LOG.EXIT, "getListOfObjects", JsonHelperUtil.class.getSimpleName());

		return mapper.readValue(data, listType);
	}

	/**
	 * Extract JSON from file
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getJsonFromFile(String filePath, String[] activeProfiles) throws IOException {

		log.info(CommonConstants.LOG.ENTRY, "getJsonFromFile", JsonHelperUtil.class.getSimpleName());

		String resultJson = "";

		log.info("Active Profile for File Loading Purposes: " + Arrays.toString(activeProfiles));

		if (Arrays.asList(activeProfiles).contains("loc")) {

			File file = new ClassPathResource(filePath).getFile();

			resultJson = new String(Files.readAllBytes(file.toPath()));
		} else {

			ClassPathResource classPathResource = new ClassPathResource(filePath);

			byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());

			resultJson = new String(binaryData, StandardCharsets.UTF_8);
		}

		log.info(CommonConstants.LOG.EXIT, "getJsonFromFile", JsonHelperUtil.class.getSimpleName());

		return resultJson;
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

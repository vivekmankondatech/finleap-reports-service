/**
 * 
 */
package com.finleap.app.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
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
@Component
@Slf4j
public class CommonUtils {

	/**
	 * Generic converter from byte array to Class T
	 * 
	 * @param <T>
	 * @param requestBytes
	 * @return
	 */
	public <T> T getObjectFromByte(Byte[] requestBytes) {
		byte[] bytes = ArrayUtils.toPrimitive(requestBytes);

		if (bytes == null) {
			log.error("Invalid byte for convertion");
			return null;
		}

		return SerializationUtils.deserialize(bytes);
	}

}

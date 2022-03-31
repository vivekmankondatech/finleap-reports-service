/**
 * 
 */
package com.finleap.app.common.util;

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
 * @since 1 Apr 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		1 Apr 2022						Initial commit
 * 
 * </pre>
 */
public class DateTimeUtils {
	private DateTimeUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * For example: 2018-12-28
	 */
	public static final String DATE = "yyyy-MM-dd";
	/**
	 * For example: 2018-12-28 10:00:00
	 */
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Example: 10:00:00
	 */
	public static final String TIME = "HHmmss";
	/**
	 * Example: 10:00
	 */
	public static final String TIME_WITHOUT_SECONDS = "HH:mm";

	/**
	 * For example: 2018-12-28 10:00
	 */
	public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

}

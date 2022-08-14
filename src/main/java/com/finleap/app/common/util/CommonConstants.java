/**
 * 
 */
package com.finleap.app.common.util;

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
 * @since 20 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		20 Mar 2022						Initial commit
 * 
 * </pre>
 */
public interface CommonConstants {

	/* For log messages */
	public interface LOG {
		/* Message while entering into the method */
		public String ENTRY = "ENTERING METHOD: {} IN CLASS: {}";
		/* Message while exiting from the method */
		public String EXIT = "EXITING METHOD: {} IN CLASS: {}";
		/* MEssage to display some information */
		public String INFO = "INFO FROM METHOD: {} IN CLASS: {} DATA: {}";
		/* MEssage to display execution time information */
		public String EXECUTION_TIME = "EXECUTION TIME OF METHOD: {} IN CLASS: {} :: {} ms";
		/* Message to display request data */
		public String REQUEST = "REQUEST Data: {}";
		/* Message while error in method */
		public String ERROR = "EXCEPTION METHOD: {} IN CLASS: {} EXCEPTION: {}";
		/* Message to display response data */
		public String RESPONSE = "RESPONSE Data: {}";
	}

	/* Generic constants */
	public interface Generic {

		String ROLE_DELIMITER = "ROLE_";

	}

}

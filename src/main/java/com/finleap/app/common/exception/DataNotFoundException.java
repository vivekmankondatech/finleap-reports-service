/**
 * 
 */
package com.finleap.app.common.exception;

import org.springframework.http.HttpStatus;

import com.finleap.app.common.exception.base.BaseUncheckedException;

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.exception | finleap-reports-service
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
public class DataNotFoundException extends BaseUncheckedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "data.not-found";
	private static final String ERROR_CODE = "HT_404";

	public DataNotFoundException() {
		super(MESSAGE, ERROR_CODE, HttpStatus.NOT_FOUND);
	}

	public DataNotFoundException(String errMsg) {
		super(errMsg, ERROR_CODE, HttpStatus.NOT_FOUND);
	}

	public DataNotFoundException(String errMsg, Object[] args) {
		super(errMsg, args, ERROR_CODE, HttpStatus.NOT_FOUND);
	}

	public DataNotFoundException(String errMsg, Throwable cause) {
		super(errMsg, cause, ERROR_CODE, HttpStatus.NOT_FOUND);
	}
}

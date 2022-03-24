/**
 * 
 */
package com.finleap.app.common.exception;

import org.springframework.http.HttpStatus;

import com.finleap.app.common.exception.base.BaseUncheckedException;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.exception | hike-trail-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 10 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		10 Mar 2022						Initial commit
 * 
 * </pre>
 */
public class NotAcceptableException extends BaseUncheckedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "not-acceptable.error";
	private static final String ERROR_CODE = "HTA_406";

	public NotAcceptableException() {
		super(MESSAGE, ERROR_CODE, HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(String errMsg) {
		super(errMsg, ERROR_CODE, HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(String errMsg, Object[] args) {
		super(errMsg, args, ERROR_CODE, HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(String errMsg, String errCode) {
		super(errMsg, errCode, HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(String errMsg, Throwable cause) {
		super(errMsg, cause, ERROR_CODE, HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(String errMsg, Throwable cause, String errorCode) {
		super(errMsg, cause, errorCode, HttpStatus.NOT_ACCEPTABLE);
	}
}

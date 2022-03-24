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
public class DataExchangeException extends BaseUncheckedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "interal-server.error";
	private static final String ERROR_CODE = "HTA_500";

	public DataExchangeException() {
		super(MESSAGE, ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public DataExchangeException(String errMsg) {
		super(errMsg, ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public DataExchangeException(String errMsg, Object[] args) {
		super(errMsg, args, ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public DataExchangeException(String errMsg, Throwable cause) {
		super(errMsg, cause, ERROR_CODE);
	}

	public DataExchangeException(Throwable cause) {
		super(MESSAGE, cause, ERROR_CODE);
	}

	public DataExchangeException(String errMsg, String errCode) {
		super(errMsg, errCode);
	}

	public DataExchangeException(String errMsg, Throwable cause, String errCode) {
		super(errMsg, cause, errCode);
	}

	public DataExchangeException(String errMsg, String errCode, HttpStatus httpStatus) {
		super(errMsg, errCode, httpStatus);
	}

	public DataExchangeException(String errMsg, Throwable cause, String errCode, HttpStatus httpStatus) {
		super(errMsg, cause, errCode, httpStatus);
	}
}

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
public class FinleapReportsBaseException extends BaseUncheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "interal-server.error";
	private static final String ERROR_CODE = "STP_500";

	public FinleapReportsBaseException() {
		super(MESSAGE, ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public FinleapReportsBaseException(String errMsg) {
		super(errMsg, ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public FinleapReportsBaseException(String errMsg, Throwable cause) {
		super(errMsg, cause, ERROR_CODE);
	}

	public FinleapReportsBaseException(Throwable cause) {
		super(MESSAGE, cause, ERROR_CODE);
	}

	public FinleapReportsBaseException(String errMsg, String errCode) {
		super(errMsg, errCode);
	}

	public FinleapReportsBaseException(String errMsg, Throwable cause, String errCode) {
		super(errMsg, cause, errCode);
	}

	public FinleapReportsBaseException(String errMsg, String badRequest, HttpStatus httpStatus) {
		super(errMsg, badRequest, httpStatus);
	}

	public FinleapReportsBaseException(String errMsg, Throwable cause, String badRequest, HttpStatus httpStatus) {
		super(errMsg, cause, badRequest, httpStatus);
	}
}

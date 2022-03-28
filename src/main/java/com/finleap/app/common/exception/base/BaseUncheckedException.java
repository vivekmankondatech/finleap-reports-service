/**
 * 
 */
package com.finleap.app.common.exception.base;

import org.springframework.http.HttpStatus;

/**
 * 
 * Any License information can go here
 */
 
/**
 * 
 * com.finleap.app.common.exception.base | finleap-reports-service
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
public class BaseUncheckedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorCode;

	private final HttpStatus status;

	@SuppressWarnings("unused")
	private Object[] args;

	public BaseUncheckedException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public BaseUncheckedException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public BaseUncheckedException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public BaseUncheckedException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public BaseUncheckedException(String message, String errorCode, HttpStatus status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	public BaseUncheckedException(String message, Object[] args, String errorCode, HttpStatus status) {
		super(message);
		this.args = args;
		this.errorCode = errorCode;
		this.status = status;
	}

	public BaseUncheckedException(String message, Throwable cause, String errorCode, HttpStatus status) {
		super(message, cause);
		this.errorCode = errorCode;
		this.status = status;
	}

	public BaseUncheckedException(Throwable cause, String errorCode, HttpStatus status) {
		super(cause);
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public Object[] getArgs() {
		return this.args;
	}
}

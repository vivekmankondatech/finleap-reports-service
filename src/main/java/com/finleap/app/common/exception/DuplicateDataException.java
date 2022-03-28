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
public class DuplicateDataException extends BaseUncheckedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "data.duplicate";
	private static final String ERROR_CODE = "HTA_409";

	public DuplicateDataException() {
		super(MESSAGE, ERROR_CODE, HttpStatus.CONFLICT);
	}

	public DuplicateDataException(String errMsg) {
		super(errMsg, ERROR_CODE, HttpStatus.CONFLICT);
	}

	public DuplicateDataException(String errMsg, Object[] args) {
		super(errMsg, args, ERROR_CODE, HttpStatus.CONFLICT);
	}

	public DuplicateDataException(String errMsg, Throwable cause) {
		super(errMsg, cause, ERROR_CODE, HttpStatus.CONFLICT);
	}
}
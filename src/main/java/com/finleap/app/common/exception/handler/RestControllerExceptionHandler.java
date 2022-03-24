/**
 * 
 */
package com.finleap.app.common.exception.handler;

import java.nio.file.AccessDeniedException;
import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.finleap.app.common.exception.DataExchangeException;
import com.finleap.app.common.exception.DataNotFoundException;
import com.finleap.app.common.exception.DuplicateDataException;
import com.finleap.app.common.exception.InvalidArgumentException;
import com.finleap.app.common.exception.NotAcceptableException;
import com.finleap.app.common.exception.base.BaseExceptionHandler;
import com.finleap.app.common.exception.base.BaseUncheckedException;
import com.finleap.app.common.exception.dto.ApiError;

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.exception.handler | finleap-reports-service
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
@ControllerAdvice
public class RestControllerExceptionHandler extends BaseExceptionHandler {

	private final MessageSource messageSource;

	protected RestControllerExceptionHandler(MessageSource messageSource) {
		super(messageSource);
		this.messageSource = messageSource;
	}

	/**
	 * Error type 400 <br/>
	 * 
	 * Handle all constrain validation exceptions <br/>
	 * https://stackoverflow.com/questions/45070642/springboot-doesnt-handle-org-hibernate-exception-constraintviolationexception
	 * 
	 * List of exceptions that fall under this category:
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request,
			Locale locale) {
		String errorMessage = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);

		// @formatter:off
		ApiError errorDetails = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(errorMessage)
				.trace(request.getDescription(false))
				.build();
		// @formatter:on
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Error type: 400 <br/>
	 * Invalid conversion errors
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<Object> handleConflict(RuntimeException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request, Locale locale) {
		String errorMessage = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);

		// @formatter:off
		ApiError errorDetails = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(errorMessage)
				.trace(request.getDescription(false))
				.build();
		// @formatter:on
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Error type 400 <br/>
	 * 
	 * Handle all bad request exceptions
	 * 
	 * List of exceptions that fall under this category:
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ InvalidArgumentException.class })
	public final ResponseEntity<ApiError> handleBadRequestExceptions(InvalidArgumentException ex, WebRequest request,
			Locale locale) {
		return generateResponseforBaseUncheckedException(ex, request, locale);
	}

	/**
	 * Error type 401 <br/>
	 * Handle access denied error
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	public final ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		// @formatter:off
		ApiError errorDetails = ApiError.builder()
		    		.status(HttpStatus.UNAUTHORIZED.value())
		    		.error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
					.message(ex.getMessage())
					.trace(request.getDescription(false))
					.build();
		    // @formatter:on
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Error type 404 <br/>
	 * 
	 * Handle all not found business exceptions
	 * 
	 * List of exceptions that fall under this category:
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ DataNotFoundException.class })
	public final ResponseEntity<ApiError> handleNotFoundExceptions(BaseUncheckedException ex, WebRequest request,
			Locale locale) {
		return generateResponseforBaseUncheckedException(ex, request, locale);
	}

	/**
	 * Error type 406 <br/>
	 * Handle NotAcceptableException exceptions
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ NotAcceptableException.class })
	public final ResponseEntity<ApiError> handlePlacementServiceException(BaseUncheckedException ex, WebRequest request,
			Locale locale) {
		return generateResponseforBaseUncheckedException(ex, request, locale);
	}

	/**
	 * Error type 409 <br/>
	 * Handle DuplicateDataException exceptions
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ DuplicateDataException.class })
	public final ResponseEntity<ApiError> handleConflictExceptions(DuplicateDataException ex, WebRequest request,
			Locale locale) {
		return generateResponseforBaseUncheckedException(ex, request, locale);
	}

	/**
	 * Error type 500 <br/>
	 * Handle DataExchangeException exceptions
	 * 
	 * @param ex
	 * @param request
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({ DataExchangeException.class })
	public final ResponseEntity<ApiError> handleDataExchangeException(DataExchangeException ex, WebRequest request,
			Locale locale) {
		return generateResponseforBaseUncheckedException(ex, request, locale);
	}

	/**
	 * Create response of BaseUncheckedException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	protected ResponseEntity<ApiError> generateResponseforBaseUncheckedException(BaseUncheckedException ex,
			WebRequest request, Locale locale) {
		String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), ex.getMessage(), locale);

		// @formatter:off
		ApiError errorDetails = ApiError.builder()
				.status(ex.getStatus().value())
	    		.error(ex.getStatus().getReasonPhrase())
	    		.errorCode(ex.getErrorCode())
				.message(errorMessage)
				.trace(request.getDescription(false))
				.build();
		    // @formatter:on
		return new ResponseEntity<>(errorDetails, ex.getStatus());
	}
}

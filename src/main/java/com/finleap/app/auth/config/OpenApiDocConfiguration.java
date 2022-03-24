/**
 * 
 */
package com.finleap.app.auth.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.auth.config | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 24 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		24 Mar 2022						Initial commit
 * 
 * </pre>
 */

@Configuration
//@formatter:off
@OpenAPIDefinition(
		info = @Info
		(title = "Finleap Reports Service API",
		version = "1.0",
		description = "Report Information")
		)
@SecurityScheme(
		name = "finleap-auth",
		description = "Authentication for Finleap Application",
		scheme = "basic",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER)
//@formatter:on
public class OpenApiDocConfiguration {

}

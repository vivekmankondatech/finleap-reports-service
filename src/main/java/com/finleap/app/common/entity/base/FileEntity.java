/**
 * 
 */
package com.finleap.app.common.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Any License information can go here
 */

/**
 * 
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.entity.base | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 19 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		19 Mar 2022						Initial commit
 * 
 * </pre>
 */
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FileEntity extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "original_file_name")
	private String originalFileName;

	@Column(name = "mime_type")
	private String mimeType;

}
package com.finleap.app.common.entity.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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
public class TimestampEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "created_date_time", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT timezone('utc'::text, now())")
	private LocalDateTime createdDateTime;

	@Column(name = "last_updated_date_time", columnDefinition = "timestamp without time zone NOT NULL DEFAULT timezone('utc'::text, now())")
	private LocalDateTime lastUpdatedDateTime;

	@PrePersist
	protected void onTimeStampEntityCreate() {
		lastUpdatedDateTime = createdDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onTimeStampEntityUpdate() {
		lastUpdatedDateTime = LocalDateTime.now();
	}

}

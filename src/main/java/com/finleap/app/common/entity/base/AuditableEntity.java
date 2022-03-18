package com.finleap.app.common.entity.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class) // For auditing automatically
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuditableEntity extends TimestampEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private UUID createdBy;

//	@LastModifiedBy - Not required as this is handled by Audit listener Implementation
	@Column(name = "last_updated_by")
	private UUID lastUpdatedBy;

	@PrePersist
	public void prePersist() {
		if (lastUpdatedBy == null)
			this.lastUpdatedBy = this.createdBy;
	}

}

/**
 * 
 */
package com.finleap.app.report.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import com.finleap.app.common.entity.base.FileEntity;
import com.finleap.app.report.converter.IncidentReportStatusConverter;
import com.finleap.app.report.entity.enums.IncidentReportStatus;
import com.finleap.app.user.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

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
 * com.finleap.app.report.entity | finleap-reports-service
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
@Entity
@Table(name = "incident_reports")
@Getter
@Setter
//Soft deleteO
@SQLDelete(sql = "UPDATE incident_reports SET is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class IncidentReport extends FileEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "status", columnDefinition = "bigint")
	@Convert(converter = IncidentReportStatusConverter.class)
	private IncidentReportStatus status;

	@ManyToOne
	@JoinColumn(name = "assignee", foreignKey = @ForeignKey(name = "incident_reports_users_fk"))
	@NotNull
	private User assignee;

}

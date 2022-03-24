/**
 * 
 */
package com.finleap.app.user.entity;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.finleap.app.auth.converter.PasswordEncoderConverter;
import com.finleap.app.common.entity.base.AuditableEntity;
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
 * com.finleap.app.user.entity | finleap-reports-service
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
@Table(name = "users")
@Getter
@Setter
//Soft deleteO
@SQLDelete(sql = "UPDATE users SET is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "email_id", unique = true, nullable = false)
	private String emailId;

	@Column(updatable = false, name = "password", columnDefinition = "text NOT NULL")
	@Convert(converter = PasswordEncoderConverter.class)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(name = "comments")
	private String comments;

	@ManyToOne
	@JoinColumn(name = "user_role_id", foreignKey = @ForeignKey(name = "users_user_role_fk"))
	@NotNull
	private UserRole userRole;

	/*
	 * Auth-related fields
	 */
	@Column(name = "enabled", columnDefinition = "boolean NOT NULL DEFAULT false")
	private boolean enabled;

	@Column(name = "password_reset", columnDefinition = "boolean NOT NULL DEFAULT false")
	private boolean passwordReset;

	@Column(name = "account_expired")
	private boolean accountExpired;

	@Column(name = "account_locked")
	private boolean accountLocked;

	@Column(name = "credentials_expired")
	private boolean credentialsExpired;

	/**
	 * @param b
	 */
	public User(User user) {

		super(user.getCreatedBy(), user.getLastUpdatedBy());

		this.firstName = user.firstName;
		this.middleName = user.middleName;
		this.lastName = user.lastName;
		this.age = user.age;
		this.emailId = user.emailId;
		this.password = user.password;
		this.comments = user.comments;
		this.userRole = user.userRole;
		this.enabled = user.enabled;
		this.passwordReset = user.passwordReset;
		this.accountExpired = user.accountExpired;
		this.accountLocked = user.accountLocked;
		this.credentialsExpired = user.credentialsExpired;
	}

}

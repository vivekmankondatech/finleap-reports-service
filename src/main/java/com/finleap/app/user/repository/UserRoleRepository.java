/**
 * 
 */
package com.finleap.app.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finleap.app.user.entity.UserRole;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.user.repository | finleap-reports-service
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
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

	boolean existsByName(String name);

	Optional<UserRole> findByName(String customer);

}

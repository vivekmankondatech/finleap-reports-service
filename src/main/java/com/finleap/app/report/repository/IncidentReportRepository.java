/**
 * 
 */
package com.finleap.app.report.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finleap.app.report.entity.IncidentReport;
import com.finleap.app.report.entity.enums.IncidentReportStatus;
import com.finleap.app.user.entity.User;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.report.repository | finleap-reports-service
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
@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, UUID> {

	List<IncidentReport> findByAssignee(User assignee);

	List<IncidentReport> findByStatus(IncidentReportStatus status);

	List<IncidentReport> findByTitle(String title);

	IncidentReport findByAssigneeAndStatus(User assignee, IncidentReportStatus status);

}

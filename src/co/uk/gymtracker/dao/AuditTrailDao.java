package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.audit.Audit;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
@Component
public class AuditTrailDao extends GenericDao {

    /**
     * find all audit record documents
     *
     * @return
     */
    public List<Audit> findAllAuditRecords() {
        return mongoOperations.findAll(Audit.class);
    }

    /**
     * find audit records that are associated with a GymUser
     *
     * @param userId
     * @return
     */
    public List<Audit> findAllAuditRecordsByUserId(String userId) {
        return mongoOperations.find(new Query(Criteria.where("username").is(userId)), Audit.class);
    }

    /**
     * save an Audit record by GymUser
     *
     * @param audit
     */
    public void saveAuditRecordByUsername(Audit audit) {
        mongoOperations.save(audit);
    }

}

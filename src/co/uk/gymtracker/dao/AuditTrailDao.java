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
public class AuditTrailDao extends GymGenericDao {

    public List<Audit> findAllAuditRecords() {
        return mongoOperations.findAll(Audit.class);
    }

    public List<Audit> findAllAuditRecordsByUserId(String userId) {
        return mongoOperations.find(new Query(Criteria.where("userId").is(userId)), Audit.class);
    }

    public void saveAuditRecordByUsername(Audit audit) {
        mongoOperations.save(audit);
    }

}

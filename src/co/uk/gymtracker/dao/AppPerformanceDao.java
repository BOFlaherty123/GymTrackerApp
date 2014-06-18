package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.performance.PerformanceLog;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/06/2014
 * @project GymTrackerApp
 */
@Component
public class AppPerformanceDao extends GymGenericDao {

    /**
     *
     * @return
     */
    public List<PerformanceLog> findAllPerformanceLogs() {
        return mongoOperations.find(new Query().limit(15), PerformanceLog.class);
    }

    /**
     *
     * @return
     */
    public List<PerformanceLog> findAllSlowQueries() {

        Query query = new Query();
        query.addCriteria(Criteria.where("slowQuery").is(true));

        return mongoOperations.find(query, PerformanceLog.class);
    }
}

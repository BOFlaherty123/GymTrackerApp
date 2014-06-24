package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.performance.PerformanceLog;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Application Performance DAO
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/06/2014
 * @project GymTrackerApp
 */
@Component
public class AppPerformanceDao extends GymGenericDao {

   public void insertPerformanceLog(PerformanceLog performanceLog) {

        Long id = (long) (mongoOperations.findAll(PerformanceLog.class).size() + 1);
        performanceLog.setId(id);

        mongoOperations.insert(performanceLog);
   }

    /**
     *
     * @return
     */
    public List<PerformanceLog> findAllPerformanceLogs() {

        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        query.limit(15);

        return mongoOperations.find(query, PerformanceLog.class);
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

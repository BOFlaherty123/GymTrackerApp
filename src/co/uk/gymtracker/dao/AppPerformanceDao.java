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
     * find all PerformanceLog documents
     *
     * @return
     */
    public List<PerformanceLog> findAllPerformanceLogs() {
        return mongoOperations.find(new Query().with(new Sort(Sort.Direction.DESC, "_id")).limit(15), PerformanceLog.class);
    }

    /**
     * find all slow queries within the PerformanceLog mongo document
     *
     * @return
     */
    public List<PerformanceLog> findAllSlowQueries() {
        return mongoOperations.find(new Query().addCriteria(Criteria.where("slowQuery").is(true)), PerformanceLog.class);
    }
}

package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.performance.PerformanceLog;
import co.uk.gymtracker.model.stats.Collection;
import co.uk.gymtracker.model.stats.Database;
import com.mongodb.CommandResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Application Performance DAO
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/06/2014
 * @project GymTrackerApp
 */
@Component
public class AppPerformanceDao extends GenericDao {

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
        return mongoOperations.find(new Query().with(new Sort(Sort.Direction.DESC, "_id")).limit(30), PerformanceLog.class);
    }

    /**
     * find all slow queries within the PerformanceLog mongo document
     *
     * @return
     */
    public List<PerformanceLog> findAllSlowQueries() {
        return mongoOperations.find(new Query().addCriteria(Criteria.where("slowQuery").is(true)), PerformanceLog.class);
    }

    /**
     * retrieves and displays Database statistics
     *
     * @return
     */
    public Database retrieveDatabaseInformation() {

        CommandResult commandResult = mongoOperations.getCollection("gymUser").getDB().getStats();
        Database database = new Database();
        database.setName(commandResult.getString("db"));
        database.setServer(commandResult.getString("serverUsed"));
        database.setSize(commandResult.getString("fileSize"));
        database.setNumOfCollections(commandResult.getString("collections"));
        database.setNumbOfObjects(commandResult.getString("objects"));
        database.setAvgObjSize(commandResult.getString("avgObjSize"));

        Set<String> dbCollectionNames =  mongoOperations.getCollection("gymUser").getDB().getCollectionNames();

        List<Collection> dbCollections = new ArrayList<Collection>();

        for(String collectionName : dbCollectionNames) {

            Collection col = new Collection();

            CommandResult collectionStats = mongoOperations.getCollection(collectionName).getStats();
            col.setName(collectionStats.getString("ns"));
            col.setNumberOfDocuments(collectionStats.getString("count"));
            col.setSize(collectionStats.getString("size"));
            col.setAvgObjectSize(collectionStats.getString("avgObjSize"));

            dbCollections.add(col);

        }

        database.setCollections(dbCollections);

        return database;
    }

}

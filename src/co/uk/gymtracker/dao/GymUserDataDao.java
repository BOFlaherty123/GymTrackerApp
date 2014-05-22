package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymLogData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Dao for performing CRUD actions on the GymLogData object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/04/14
 * @project GymTrackerApp
 */
@Component
public class GymUserDataDao extends GymGenericDao {

//        Query recordQuery = query(where("activity").is("Cycling"));
//        List<GymLogData> recordyResult = mongoOperations.find(recordQuery, GymLogData.class);
//        System.out.println("recordyResult.size() " + recordyResult.size());

    public List<GymLogData> findAllUserGymData() {
        return mongoOperations.findAll(GymLogData.class);
    }

    public void saveUserGymData(GymLogData gymLogData) {

        if(!mongoOperations.collectionExists(GymLogData.class)) {
            mongoOperations.createCollection(GymLogData.class);
        }

        mongoOperations.insert(gymLogData);
    }

}

package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao for performing CRUD actions on the GymLogData object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/04/14
 * @project GymTrackerApp
 */
@Component
public class GymUserDataDao extends GenericDao {

    /**
     * finds all GymLogData for a user
     *
     * @param userId
     * @return
     */
    public List<GymLogData> findAllUserGymData(String userId) {
        return mongoOperations.find(new Query(Criteria.where("userId").is(userId)), GymLogData.class);
    }

    /**
     * saves GymLogData to the database table
     *
     * @param gymLogData
     */
    public void saveGymLogData(GymLogData gymLogData) {
        mongoOperations.save(gymLogData);
    }

    @SuppressWarnings("unchecked")
    public List<GymLogData> findGymUserDataByActivity(GymUser gymUser, String activity) {

        logger.entry(gymUser, activity);

        List<GymLogData> gymDataByUser = findAllUserGymData(gymUser.getId());

        List<GymLogData> allGymSessions = new ArrayList<>();
        List<GymLogData> gymSessionsForUser = new ArrayList<>();

        for(GymLogData gld : gymDataByUser) {

            // TODO - needs a complete refactor to run through ExerciseCardio and ExerciseWeight
            if(gld.getExerciseCardio() != null) {

                for(ExerciseCardio cardioExercise : gld.getExerciseCardio())  {

                    if(cardioExercise.getExercise().equals(activity)) {
                        gymSessionsForUser.add(gld);
                        logger.info("added [" + gld.toString() + "] to gymSessionByActivity collection.");
                    } else if (activity.equals("ALL")) {
                        allGymSessions.add(gld);
                        logger.info("added [" + gld.toString() + "] to allGymSessions collection.");
                    }

                }

            }

        }

        logger.exit();

        return (activity.equals("ALL")) ?  allGymSessions :  gymSessionsForUser;
    }

}

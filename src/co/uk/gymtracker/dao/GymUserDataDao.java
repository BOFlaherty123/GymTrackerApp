package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
public class GymUserDataDao extends GymGenericDao {

    public List<GymLogData> findAllUserGymData() {
        return mongoOperations.findAll(GymLogData.class);
    }

    @SuppressWarnings("unchecked")
    public List<GymLogData> findGymUserDataByActivity(GymUser gymUser, String activity) {

        logger.entry(gymUser, activity);

        DBCollection user = mongoOperations.getCollection("gymUser");

        BasicDBObject query = new BasicDBObject("username", gymUser.getUsername());
        DBCursor cursor = user.find(query);

        List<GymLogData> gymSessionsForUser = new ArrayList<GymLogData>();
        List<GymLogData> allGymSessions = new ArrayList<GymLogData>();

        try {
            while(cursor.hasNext()) {
                BasicDBObject result = (BasicDBObject) cursor.next();

                ArrayList<BasicDBObject> userSessions = (ArrayList<BasicDBObject>) result.get("userSessions");

                if(userSessions != null) {

                    for (BasicDBObject session : userSessions) {

                        GymLogData gymSessionData = new GymLogData(
                                (String) session.get("date"), (String) session.get("duration"), (String) session.get("cardioExercise"),
                                (String) session.get("activityDuration"), (String) session.get("distance"), (String) session.get("level"),
                                (String) session.get("weight"), (String) session.get("reps"), (String) session.get("calories"),
                                (String) session.get("userWeight"), "exercise"
                        );

                        if(activity.equals("ALL")) {
                            allGymSessions.add(gymSessionData);
                        } else {

                            if (session.get("activity").equals(activity)) {
                                gymSessionsForUser.add(gymSessionData);
                            }

                        }

                    }

                }
            }
        } finally {
            cursor.close();
        }

        logger.exit();

        return (activity.equals("ALL")) ?  allGymSessions :  gymSessionsForUser;
    }

}

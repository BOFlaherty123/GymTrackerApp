package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(GymUserDataDao.class);

    /**
        String textUri = "mongodb://admin:gymuser@ds061938.mongolab.com:61938/gymtracker";

        MongoClientURI uri = new MongoClientURI(textUri);

        MongoClient client = new MongoClient(uri);
        DB mongoDb = client.getDB(uri.getDatabase());

        System.out.println(mongoDb.getName());
        System.out.println(mongoDb.isAuthenticated());
        System.out.println(mongoDb.getMongo().getWriteConcern());

        System.out.println("obtain the user collection");
        DBCollection userCol = mongoDb.getCollection("user");

        System.out.println("add a user to the collection");
        BasicDBObject aUser = new BasicDBObject("firstName", "Benjamin")
                .append("lastName", "OFlaherty")
                .append("username", "BOFlaherty")
                .append("age", "28")
                .append("email", "ben@oflaherty.com");

        System.out.println("insert into the collection");
        System.out.println(aUser.toString());

        // TODO - Test connection from outside of the proxy
        //userCol.insert(aUser);

        client.close();

     */
    public List<GymLogData> findAllUserGymData() {
        return mongoOperations.findAll(GymLogData.class);
    }

    @SuppressWarnings("unchecked")
    public List<GymLogData> findGymUserDataByActivity(GymUser gymUser, String activity) {

        DBCollection user = mongoOperations.getCollection("gymUser");

        BasicDBObject query = new BasicDBObject("username", gymUser.getUsername());
        DBCursor cursor = user.find(query);

        List<GymLogData> gymSessionsForUser = new ArrayList<>();

        try {
            while(cursor.hasNext()) {
                BasicDBObject result = (BasicDBObject) cursor.next();

                ArrayList<BasicDBObject> userSessions = (ArrayList<BasicDBObject>) result.get("userSessions");

                if(userSessions != null) {

                    for (BasicDBObject session : userSessions) {

                        if (session.get("activity").equals(activity)) {

                            GymLogData gymSessionData = new GymLogData(
                                    (String) session.get("date"), (String) session.get("duration"), (String) session.get("activity"),
                                    (String) session.get("activityDuration"), (String) session.get("distance"), (String) session.get("levelOrWeight"),
                                    (String) session.get("calories"), (String) session.get("userWeight")
                            );

                            gymSessionsForUser.add(gymSessionData);

                        }

                    }

                }
            }
        } finally {
            cursor.close();
        }

        return gymSessionsForUser;
    }

}

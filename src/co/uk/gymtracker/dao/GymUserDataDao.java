package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymLogData;
import com.mongodb.*;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
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

    public List<GymLogData> findAllUserGymData() throws UnknownHostException {

        String textUri = "mongodb://admin:gymuser@ds061938.mongolab.com:61938/gymtracker";
        MongoClientURI uri = new MongoClientURI(textUri);

        MongoClient client = new MongoClient(uri);
        DB mongoDb = client.getDB(uri.getDatabase());

        System.out.println(mongoDb.getName());
        System.out.println(mongoDb.isAuthenticated());

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

        return mongoOperations.findAll(GymLogData.class);
    }

    public void saveUserGymData(GymLogData gymLogData) {

        if(!mongoOperations.collectionExists(GymLogData.class)) {
            mongoOperations.createCollection(GymLogData.class);
        }

        mongoOperations.insert(gymLogData);
    }

}

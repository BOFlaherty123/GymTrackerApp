package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymUser;
import com.mongodb.DBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
@Component
public class GymUserDao extends GymGenericDao {

    private static final Logger LOG = LoggerFactory.getLogger(GymUserDao.class);

    /**
     * find a gymUser object by username
     *
     * @param username
     * @return
     */
    public GymUser findGymUser(String username) {

        Query query = new Query(Criteria.where("username").is(username));

        DBCollection gymUser = mongoOperations.getCollection("gymUser");
        System.out.println(gymUser.getName());

        return mongoOperations.findOne(query, GymUser.class);
    }

    public void saveGymUser(GymUser gymUser) {
        mongoOperations.insert(gymUser);
    }

    /**
     * update the gymUser object
     *
     * @param gymUser
     */
    public void updateGymUser(GymUser gymUser) {

        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        Query query = new Query(Criteria.where("username").is(gymUser.getUsername()));

        GymUser updateUser = mongoOperations.findOne(query, GymUser.class);
        updateUser.setFirstName(gymUser.getFirstName());
        updateUser.setLastName(gymUser.getLastName());
        updateUser.setAge(gymUser.getAge());
        updateUser.setEmail(gymUser.getEmail());
        updateUser.setPassword(gymUser.getPassword());
        updateUser.setUserSessions(gymUser.getUserSessions());

        if(gymUser.getActivityAverages() != null) {
            updateUser.setActivityAverages(gymUser.getActivityAverages());
        }

        mongoOperations.save(updateUser);
        LOG.info(format("%s - updated gymUser %s", methodName, updateUser.toString()));
    }

    public void deleteUsers() {
        mongoOperations.dropCollection(GymUser.class);
    }

//    public void testing() {
//
//        String textUri = "mongodb://admin:gymuser@ds061938.mongolab.com:61938/gymtracker";
//
//        MongoClientURI uri = new MongoClientURI(textUri);
//
//        MongoClient client = null;
//
//        try {
//            client = new MongoClient(uri);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//        DB mongoDb = client.getDB(uri.getDatabase());
//        System.out.println(mongoDb.getName());
//        System.out.println(mongoDb.isAuthenticated());
//        System.out.println(mongoDb.getMongo().getWriteConcern());
//
//        System.out.println("obtain the user collection");
//        DBCollection userCol = mongoDb.getCollection("user");
//
//        System.out.println("add a user to the collection");
//        BasicDBObject aUser = new BasicDBObject("firstName", "Manual")
//        .append("lastName", "Test")
//        .append("username", "ManTest")
//        .append("age", "28")
//        .append("email", "ben@oflaherty.com");
//
//        System.out.println("insert into the collection");
//        System.out.println(aUser.toString());
//
//        // TODO - Test connection from outside of the proxy
//        userCol.insert(aUser);
//
//        client.close();
//    }

}

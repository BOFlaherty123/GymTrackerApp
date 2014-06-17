package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

/**
 * GymUser Dao
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
@Component
public class GymUserDao extends GymGenericDao {

    private static final Logger logger = LoggerFactory.getLogger(GymUserDao.class);

    /**
     * find a gymUser object by username
     *
     * @param username
     * @return
     */
    public GymUser findGymUser(String username) {

        Query query = new Query(Criteria.where("username").is(username));

        return mongoOperations.findOne(query, GymUser.class);
    }

    public List<GymUser> findAllGymUsers() {
        return mongoOperations.findAll(GymUser.class);
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
        logger.info(format("%s - updated gymUser %s", methodName, updateUser.toString()));
    }

    public void deleteUser(String username) {

        Query query = new Query(Criteria.where("username").is(username));

        mongoOperations.remove(query, GymUser.class);
    }

}

package co.uk.gymtracker.dao;

import co.uk.gymtracker.exceptions.GymUserNotFoundException;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymUserSearch;
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

    /**
     * find a gymUser object by username
     *
     * @param username
     * @return
     */
    public GymUser findGymUser(String username) {
        return mongoOperations.findOne(new Query(Criteria.where("username").is(username)), GymUser.class);
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

        logger.entry(gymUser);

        GymUser updateUser = mongoOperations.findOne(new Query(Criteria.where("username").is(gymUser.getUsername())),
                GymUser.class);

        if(updateUser != null) {

            updateUser.setFirstName(gymUser.getFirstName());
            updateUser.setLastName(gymUser.getLastName());
            updateUser.setAge(gymUser.getAge());
            updateUser.setEmail(gymUser.getEmail());
            updateUser.setPassword(gymUser.getPassword());

            if(gymUser.getActivityAverages() != null) {
                updateUser.setActivityAverages(gymUser.getActivityAverages());
            }

            mongoOperations.save(updateUser);
            logger.info(format("%s - updated gymUser %s", methodName, updateUser.toString()));

        } else {
            throw new GymUserNotFoundException("User[ " + gymUser.getUsername() + " ] not found.");
        }

        logger.exit();
    }

    public List<GymUser> findUserByCriteria(GymUserSearch searchCriteria) {

        logger.entry(searchCriteria);

        Query searchQuery = new Query(Criteria.where("username").regex(searchCriteria.getUsername()));

        // add query to search by firstName
        if (!searchCriteria.getFirstName().isEmpty()) {
            searchQuery.addCriteria(Criteria.where("firstName").regex(searchCriteria.getFirstName()));
        }
        // add query to search by lastName
        if(!searchCriteria.getLastName().isEmpty()) {
            searchQuery.addCriteria(Criteria.where("lastName").regex(searchCriteria.getFirstName()));
        }

        logger.info(format("findUserByCriteria - [ %s ]", searchQuery));

        logger.exit();

        return mongoOperations.find(searchQuery, GymUser.class);
    }

    public void deleteUser(String username) {
        mongoOperations.remove(new Query(Criteria.where("username").is(username)), GymUser.class);
    }

}

package co.uk.gymtracker.dao;

import co.uk.gymtracker.model.GymUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
@Component
public class GymUserDao extends GymGenericDao {

    public GymUser findGymUser(String username) {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoOperations.findOne(query, GymUser.class);
    }

    public void saveGymUser(GymUser gymUser) {
        mongoOperations.insert(gymUser);
    }

    public void updateGymUser(GymUser gymUser) {
        mongoOperations.save(gymUser);
    }

    public void deleteUsers() {
        mongoOperations.dropCollection(GymUser.class);
    }

}

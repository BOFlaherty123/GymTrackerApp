package co.uk.gymtracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/04/14
 * @project GymTrackerApp
 */
public class GymGenericDao {

    @Autowired
    public MongoOperations mongoOperations;


}

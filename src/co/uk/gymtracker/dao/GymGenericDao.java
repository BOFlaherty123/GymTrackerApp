package co.uk.gymtracker.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.net.UnknownHostException;

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

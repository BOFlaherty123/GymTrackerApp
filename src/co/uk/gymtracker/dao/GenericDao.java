package co.uk.gymtracker.dao;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/04/14
 * @project GymTrackerApp
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public abstract class GenericDao {

    @Autowired
    public MongoOperations mongoOperations;

    final XLogger logger = XLoggerFactory.getXLogger(GenericDao.class
            .getName());
}

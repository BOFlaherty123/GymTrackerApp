package co.uk.gymtracker.service;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.dao.GymUserDataDao;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Common setup and behavior for Service classes
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
public class AbstractGymService {

    @Autowired
    protected GymUserDao dao;

    @Autowired
    protected GymUserDataDao gymDataDao;

    final XLogger logger = XLoggerFactory.getXLogger(GymDataInputService.class
            .getName());

}

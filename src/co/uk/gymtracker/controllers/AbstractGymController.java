package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.dao.GymUserDataDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/05/14
 * @project GymTrackerApp
 */
public class AbstractGymController {

    @Autowired
    public GymUserDao userDao;

    @Autowired
    public GymUserDataDao gymDataDao;

}

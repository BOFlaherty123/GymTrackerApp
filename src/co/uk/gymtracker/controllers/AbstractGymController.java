package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.logging.PerformanceLogging;
import co.uk.gymtracker.model.GymUser;
import org.perf4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/05/14
 * @project GymTrackerApp
 */
public abstract class AbstractGymController {

    @Autowired
    public GymUserDao userDao;

    @Autowired
    public GymUserDataDao gymDataDao;

    @Autowired
    public PerformanceLogging performanceLogging;

    protected GymUser getLoggedInUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        return userDao.findGymUser(ctx.getAuthentication().getName());
    }

    protected void runPerformanceLogging(String methodName, StopWatch watch) {
        performanceLogging.isMethodProcessingBelowThreshold(methodName, watch);
    }
}

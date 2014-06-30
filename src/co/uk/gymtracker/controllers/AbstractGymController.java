package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.exceptions.GymUserNotFoundException;
import co.uk.gymtracker.logging.PerformanceLogging;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.service.GymDataInputService;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Common Controller setup and behavior
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/05/14
 * @project GymTrackerApp
 */
public abstract class AbstractGymController {

    @Autowired
    public GymUserDao userDao;

    @Autowired
    public GymDataInputService gymDataInputService;

    @Autowired
    public PerformanceLogging performanceLogging;

    protected final XLogger logger = XLoggerFactory.getXLogger(AbstractGymController.class
            .getName());

    public abstract ModelAndView executeEntryPage(ModelAndView mav);

    GymUser getLoggedInUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();

        GymUser gymUser =  userDao.findGymUser(ctx.getAuthentication().getName());

        if(gymUser != null) {
            logger.info("gymUser found [" + gymUser.getUsername() + "]");
        } else {
            throw new GymUserNotFoundException("User[ " + gymUser.getUsername() + " ] not found.");
        }

        return gymUser;
    }

    protected Slf4JStopWatch createStopWatchInstance() {
        return new Slf4JStopWatch();
    }

    protected void runPerformanceLogging(String className, String methodName, StopWatch watch) {
        performanceLogging.isMethodProcessingBelowThreshold(className, methodName, watch);
    }

    @ModelAttribute("exercises")
    public List<String> listActivities() {

        List<String> activity = new ArrayList<>();
        activity.add("Running");
        activity.add("Cycling");
        activity.add("Rowing");

        return activity;
    }

    @ModelAttribute("activityDuration")
    public List<String> listActivityDuration() {

        List<String> activityDuration = new ArrayList<>();
        activityDuration.add("");
        activityDuration.add("15");
        activityDuration.add("30");
        activityDuration.add("45");
        activityDuration.add("60");

        return activityDuration;
    }

    @ModelAttribute("userRoles")
    public List<String> availableUserRoles() {
        List<String> userRoles = new ArrayList<>();
        userRoles.add("ROLE_USER");
        userRoles.add("ROLE_ADMIN");
        userRoles.add("ROLE_DENIED");

        return userRoles;
    }

    // TODO Change ModelAttributes name and refact the UserDashboard class
    @ModelAttribute("activity")
    public List<String> listActivities2() {

        List<String> activity = new ArrayList<>();
        activity.add("");
        activity.add("Running");
        activity.add("Cycling");
        activity.add("Rowing");

        return activity;
    }
}

package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.dashboard.ActivityAverage;
import co.uk.gymtracker.model.dashboard.TargetIncrease;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * GymUserTargetController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Controller
@RequestMapping("/user")
public class GymUserDashboardController extends AbstractGymController {

    @Autowired
    public CalculateUserTargets targets;

    @Autowired
    public CalculateActivityAverages calculateAverages;

    /**
     * Setup and Display the User Dashboard
     *
     * @return
     */
    @Override
    @RequestMapping(value="/userDashboard")
    public ModelAndView processEntryPage(ModelAndView mav) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        mav.setViewName("user/userDashboard");

        // Get GymUser object
        GymUser user = getLoggedInUser();
        System.out.println("userDashboard - user sessions: " + user.getUserSessions());

        mav.addObject(user);

        // Calculate averages for dashboard display
        processUserAverages(mav, user);
        processActivityDurationPercentages(mav, user);

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, watch);

        return mav;
    }

    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid GymUser gymUser, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {
            mav.setViewName("/user/userDashboard");
            return mav;
        } else {
            GymUser user = userDao.findGymUser(gymUser.getUsername());
            gymUser.setUserSessions(user.getUserSessions());

            userDao.updateGymUser(gymUser);
        }

        mav.setViewName("redirect:/user/userDashboard");

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, watch);

        return mav;
    }

    /**
     * Deletes a GymUser object
     */
    @RequestMapping(value="/deleteUser")
    public void deleteUsers() {
        userDao.deleteUser("test");
    }

    /**
     * Call to CalculateActivityAverages to calculate and return the average distance value(s)
     *
     * @param mav
     * @return
     */
    private ModelAndView processUserAverages(ModelAndView mav, GymUser user) {

        List<ActivityAverage> averages = calculateAverages.calculateActivityAverages(user);
        for(ActivityAverage avg : averages) {
            logger.info(format("processing averages for activity: %s.", avg.getActivity()));
            mav = processActivityAverageDistances(mav, avg);
        }

        user.setActivityAverages(averages);
        userDao.updateGymUser(user);

        return mav;
    }

    /**
     *
     * @param mav
     * @param avg
     * @return
     */
    private ModelAndView processActivityAverageDistances(ModelAndView mav, ActivityAverage avg) {
        String distance = avg.getAverageDistance();
        logger.info(format("activity average distance: %s.", avg.getAverageDistance()));

        return (avg.getActivity().equals("Running")) ? mav.addObject("running_avg_distance", distance) :
                (avg.getActivity().equals("Cycling")) ? mav.addObject("cycling_avg_distance", distance) :
                        mav.addObject("rowing_avg_distance", distance);
    }

    /**
     * Calculates the percentage value for the duration of each activity
     *
     * @param mav
     * @param user
     * @return
     */
    private ModelAndView processActivityDurationPercentages(ModelAndView mav, GymUser user) {

        Map<String, BigDecimal> durationPercentages = calculateAverages.calculateAvgDurationPercentages(user);

        for(Map.Entry<String, BigDecimal> entry : durationPercentages.entrySet()) {
            mav.addObject(entry.getKey().toLowerCase() + "_duration_percent", entry.getValue().toString());
        }

        return mav;
    }

    @RequestMapping(value="/calculateTargetByPercentIncrease/{activity}/{percentage}", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> calculateTargetOnPercentageIncrease(@PathVariable("activity") String activity,
                                                                    @PathVariable("percentage") int percentage) {

        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        GymUser user = getLoggedInUser();

        // TODO - Change from return value of String to TargetIncrease (as a JSON obj? Jackson will be required)
        TargetIncrease targetIncrease = targets.calculateTargetOnPercentageIncrease(user, activity, percentage);

        Map<String,String> testingMap = new HashMap<>();
        testingMap.put("distance", targetIncrease.getDistanceIncrease());
        testingMap.put("duration", targetIncrease.getDurationIncrease());

        // log method performance
        performanceLogging.isMethodProcessingBelowThreshold(this.getClass().getName(), methodName, watch);

        return testingMap;
    }

    @ModelAttribute("activity")
    public List<String> listActivities() {

        List<String> activity = new ArrayList<>();
        activity.add("");
        activity.add("Running");
        activity.add("Cycling");
        activity.add("Rowing");

        return activity;
    }
}
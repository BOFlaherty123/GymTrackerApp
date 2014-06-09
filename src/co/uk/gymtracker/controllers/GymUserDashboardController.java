package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.TargetIncrease;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    private static final Logger LOG = LoggerFactory.getLogger(GymUserDashboardController.class);

    /**
     * Setup and Display the User Dashboard
     *
     * @return
     */
    @RequestMapping(value="/userDashboard")
    public ModelAndView displayUserDashboard(HttpServletRequest request) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        ModelAndView mav = new ModelAndView("user/userDashboard");

        // Get GymUser object
        GymUser user = getLoggedInUser();
        System.out.println("userDashboard - user sessions: " + user.getUserSessions());

        mav.addObject(user);

        // Calculate averages for dashboard display
        processUserAverages(mav, user);
        processActivityDurationPercentages(mav, user);

        // log method performance
        runPerformanceLogging(methodName, watch);

        return mav;
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
            LOG.info(format("processing averages for activity: %s.", avg.getActivity()));
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
        LOG.info(format("activity average distance: %s.", avg.getAverageDistance()));

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

        GymUser user = getLoggedInUser();

        // TODO - Change from return value of String to TargetIncrease (as a JSON obj? Jackson will be required)
        TargetIncrease targetIncrease = targets.calculateTargetOnPercentageIncrease(user, activity, percentage);

        Map<String,String> testingMap = new HashMap<>();
        testingMap.put("distance", targetIncrease.getDistanceIncrease());
        testingMap.put("duration", targetIncrease.getDurationIncrease());

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
package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value="/userDashboard")
    public ModelAndView displayUserDashboard(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("user/userDashboard");

        // Get GymUser object
        GymUser user = getLoggedInUser();

        // Calculate averages for dashboard display
        processUserAverages(mav, user);
        processActivityDurationPercentages(mav, user);

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
            // activity Distance Averages
            mav = processActivityAverageDistances(mav, avg);
        }

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

    public void calculateTarget() {
        targets.calculateTargetOnPercentageIncrease(10);
    }

}
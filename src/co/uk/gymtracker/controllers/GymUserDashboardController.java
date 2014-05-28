package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * GymUserTargetController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Controller
@RequestMapping("/user")
public class GymUserDashboardController {

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
    public ModelAndView displayUserDashboard() {

        ModelAndView mav = new ModelAndView("user/userDashboard");

        processUserAverages(mav);

        return mav;
    }

    /**
     * Call to CalculateActivityAverages to calculate and return the average distance value(s)
     *
     * @param mav
     * @return
     */
    private ModelAndView processUserAverages(ModelAndView mav) {

        // TODO - Retrieve GymUser from Session
        GymUser gymUser = new GymUser();

        List<ActivityAverage> averages = calculateAverages.calculateActivityAverages(gymUser);
        mav.addObject("averages", averages);

        return mav;
    }

    /*
        Allow the user to view their future workout targets, 5, 10, 15% over 2, 4, 6 , 8 weeks.

        @RequestMapping(value="/target/{percentage_increase}")
        @PathVariable("percentage_increase") int percentage_increase

     */
    public void calculateTarget() {
        targets.calculateTargetOnPercentageIncrease(10);
    }


}
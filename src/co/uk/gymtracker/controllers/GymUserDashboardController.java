package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

        SecurityContext ctx = SecurityContextHolder.getContext();
        GymUser user = userDao.findGymUser(ctx.getAuthentication().getName());

        processUserAverages(mav, user);

        return mav;
    }

    /**
     * Call to CalculateActivityAverages to calculate and return the average distance value(s)
     *
     * @param mav
     * @return
     */
    private ModelAndView processUserAverages(ModelAndView mav, GymUser user) {

        if(user.getUserSessions() != null) {
            for(GymLogData session : user.getUserSessions()) {
                System.out.println(session.getDate());
            }
        }

        List<ActivityAverage> averages = calculateAverages.calculateActivityAverages(user);
        mav.addObject(averages);

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

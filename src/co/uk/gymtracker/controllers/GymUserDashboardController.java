package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.service.GymUserDashboardService;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    private GymUserDashboardService gymUserDashboardService;

    /**
     * Setup and Display the User Dashboard
     *
     * @return
     */
    @Override
    @RequestMapping(value="/userDashboard")
    public ModelAndView executeEntryPage(ModelAndView mav) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        logger.entry(mav);

        Slf4JStopWatch stopWatch = createStopWatchInstance();

        mav.setViewName("user/userDashboard");

        // Get GymUser object
        GymUser user = getLoggedInUser();
        mav.addObject(user);

        // Calculate averages for dashboard display
        mav.addObject("avgDistances", gymUserDashboardService.processUserAverages(user));
        mav.addObject("avgPercents", gymUserDashboardService.processActivityDurationPercentages(user));

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, stopWatch);

        logger.exit();

        return mav;
    }

    /**
     * updates a GymUser document from end-user data submission
     *
     * @param gymUser
     * @param errors
     * @return
     */
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid GymUser gymUser, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        logger.entry(gymUser, errors);

        Slf4JStopWatch stopWatch = createStopWatchInstance();

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {
            mav.setViewName("/user/userDashboard");
            return mav;
        } else {
            userDao.updateGymUser(gymUser);
        }

        mav.setViewName("redirect:/user/userDashboard");

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, stopWatch);

        logger.exit();

        return mav;
    }

    /**
     * Deletes a GymUser object
     */
    @RequestMapping(value="/deleteUser")
    public void deleteUsers() {
        userDao.deleteUser("processUserTargetIncreases");
    }

    /**
     * calculate the end users target based on the percentage increase input
     *
     * @param activity
     * @param percentage
     * @return
     */
    @RequestMapping(value="/calculateTargetByPercentIncrease/{activity}/{percentage}", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> calculateTargetOnPercentageIncrease(@PathVariable("activity") String activity,
                                                                    @PathVariable("percentage") int percentage) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        logger.entry(activity, percentage);

        Map<String, String> targetIncreases = gymUserDashboardService.processUserTargetIncreases(getLoggedInUser(), activity, percentage);

        // log method performance
        performanceLogging.isMethodProcessingBelowThreshold(this.getClass().getName(), methodName, new Slf4JStopWatch());

        logger.exit();

        return targetIncreases;
    }

}
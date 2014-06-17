package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymUser;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Gym User Controller - User Administration, CRUD operations
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
@Controller
@RequestMapping(value="/user")
public class GymUserController extends AbstractGymController {

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
        runPerformanceLogging(methodName, watch);

        return mav;
    }

    /**
     * Deletes a GymUser object
     */
    @RequestMapping(value="/deleteUser")
    public void deleteUsers() {
        userDao.deleteUser("test");
    }

}

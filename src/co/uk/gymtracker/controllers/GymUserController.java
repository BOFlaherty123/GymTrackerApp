package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymUser;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Setup and displays the createUser form
     *
     * @return
     */
    @RequestMapping(value="/createUser", method = RequestMethod.GET)
    public ModelAndView displayCreateUserForm() {

        ModelAndView mav = new ModelAndView("user/createUser");
        mav.addObject(new GymUser());

        return mav;
    }

    /**
     * Create a new GymUser object
     *
     * @param gymUser
     * @param errors
     * @return
     */
    @RequestMapping(value="/submitUser", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid GymUser gymUser, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {
            mav.setViewName("/user/createUser");

            return mav;
        } else {
            userDao.saveGymUser(gymUser);
        }

        mav.setViewName("redirect:/userLog/show");

        // log method performance
        runPerformanceLogging(methodName, watch);

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
        runPerformanceLogging(methodName, watch);

        return mav;
    }

    /**
     * Deletes a GymUser object
     */
    @RequestMapping(value="/deleteUsers")
    public void deleteUsers() {
        userDao.deleteUsers();
    }

    @ModelAttribute("userRoles")
    public List<String> availableUserRoles() {
        List<String> userRoles = new ArrayList<>();
        userRoles.add("ROLE_USER");
        userRoles.add("ROLE_ADMIN");
        userRoles.add("ROLE_DENIED");

        return userRoles;
    }

}

package co.uk.gymtracker.controllers.admin;

import co.uk.gymtracker.controllers.AbstractGymController;
import co.uk.gymtracker.model.GymUser;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Admin Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/06/2014
 * @project GymTrackerApp
 */
@Controller
@RequestMapping(value="/admin")
public class AdminUserController extends AbstractGymController {

    @RequestMapping(value="/dashboard")
    public ModelAndView displayAdminPage() {
        return new ModelAndView("admin/admin");
    }

    /**
     * Setup and displays the createUser form
     *
     * @return
     */
    @RequestMapping(value="/createUser", method = RequestMethod.GET)
    public ModelAndView displayCreateUserForm() {

        ModelAndView mav = new ModelAndView("admin/createUser");
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
            mav.setViewName("admin/createUser");

            return mav;
        } else {
            userDao.saveGymUser(gymUser);
        }

        mav.setViewName("redirect:/admin/dashboard");

        // log method performance
        runPerformanceLogging(methodName, watch);

        return mav;
    }

    /**
     *
     */
    @RequestMapping(value="/user/search")
    public void findUserByUsername() {
        System.out.println("find a user by username");
    }

    /**
     * Display a list of users to modify
     *
     * @return List<GymUser>
     */
    @RequestMapping(value="/editUser")
    public ModelAndView editUser() {

        ModelAndView mav = new ModelAndView("admin/editUser");

        List<GymUser> gymUsers = userDao.findAllGymUsers();
        mav.addObject(gymUsers);

        return mav;
    }

    /**
     * delete a registered user by {username}
     *
     * @param username
     * @return
     */
    @RequestMapping(value="/deleteUser/{username}")
    public ModelAndView deleteUser(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("redirect:/admin/editUser");

        // delete the user by id
        userDao.deleteUser(username);

        // retrieve all active users
        mav.addObject(userDao.findAllGymUsers());

        return mav;
    }

}

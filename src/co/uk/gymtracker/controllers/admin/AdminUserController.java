package co.uk.gymtracker.controllers.admin;

import co.uk.gymtracker.controllers.AbstractGymController;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.audit.Audit;
import co.uk.gymtracker.model.form.GymUserSearch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;

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

    /**
     * Setup and displays the createUser form
     *
     * @return
     */
    @Override
    @RequestMapping(value="/createUser", method = RequestMethod.GET)
    public ModelAndView executeEntryPage(ModelAndView mav) {

        mav.setViewName("admin/createUser");
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

        Slf4JStopWatch stopWatch = createStopWatchInstance();

        ModelAndView mav = new ModelAndView();

        Audit auditRecord = createAudit(this.getClass().getName(), methodName, getLoggedInUser(), gymUser.toString());

        if(errors.hasErrors()) {
            mav.setViewName("admin/createUser");

            return mav;
        } else {
            userDao.saveGymUser(gymUser);
            logger.info(format("Create User [ %s ] ", gymUser.toString()));
        }

        mav.setViewName("redirect:/admin/dashboard");

        // save audit record to db
        auditRecord.setTimeElapsed(String.valueOf(stopWatch.getElapsedTime()));
        auditDao.saveAuditRecordByUsername(auditRecord);

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, stopWatch);

        return mav;
    }

    /**
     * Display a list of users to modify
     *
     * @return List<GymUser>
     */
    @RequestMapping(value="/editUser")
    public ModelAndView editUser() {

        ModelAndView mav = new ModelAndView("admin/editUser");
        mav.addObject(new GymUserSearch());

        List<GymUser> gymUsers = userDao.findAllGymUsers();
        mav.addObject(gymUsers);

        return mav;
    }

    /**
     *
     * @param gymUserSearch
     * @param mav
     * @param error
     * @return
     */
    @RequestMapping(value="/user/search")
    public ModelAndView findUserByUsername(@Valid GymUserSearch gymUserSearch, ModelAndView mav, Errors error) {

        mav.setViewName("admin/editUser");

        if(error.hasErrors()) {
            return mav;
        } else {
            List<GymUser> searchUsers = userDao.findUserByCriteria(gymUserSearch);
            mav.addObject(searchUsers);

            logger.info(format("searchUsers returned [" + searchUsers + "]"));
        }

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

package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymUser;
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

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {

            mav.setViewName("/user/createUser");

            return mav;

        } else {
            userDao.saveGymUser(gymUser);
        }

        mav.setViewName("redirect:/userLog/show");

        return mav;

    }

    /**
     * Deletes a GymUser object
     */
    @RequestMapping(value="/deleteUsers")
    public void deleteUsers() {
        userDao.deleteUsers();
    }

}

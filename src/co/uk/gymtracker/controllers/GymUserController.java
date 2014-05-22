package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
public class GymUserController {

    @Autowired
    public GymUserDao dao;

    @RequestMapping(value="/createUser", method = RequestMethod.GET)
    public ModelAndView displayCreateUserForm() {

        ModelAndView mav = new ModelAndView("user/createUser");
        mav.addObject(new GymUser());

        return mav;
    }

    @RequestMapping(value="/createUser", method = RequestMethod.GET, headers = "content-type=application/xml")
    public ModelAndView displayCreateUserFormXML() {
        return new ModelAndView("welcome");
    }

    @RequestMapping(value="/submitUser", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid GymUser gymUser, BindingResult result) {

        if(result.hasErrors()) {

            for(FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField());
                System.out.println(error.getDefaultMessage());
            }

            return new ModelAndView("redirect:/user/createUser");

        } else {
            dao.saveGymUser(gymUser);
        }

        return new ModelAndView("redirect:/userLog/show");

    }

    @RequestMapping(value="/deleteUsers")
    public void deleteUsers() {
        dao.deleteUsers();
    }

}

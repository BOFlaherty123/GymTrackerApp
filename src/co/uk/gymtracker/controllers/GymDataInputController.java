package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymSessionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * GymDataInputController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Controller
public class GymDataInputController {

    @Autowired
    private GymUserDataDao dao;

    /**
     * Displays the addGymSessionForm to the user
     *
     * @return
     */
    @RequestMapping(value="/addGymSessionForm")
    public ModelAndView displayGymSessionForm() {

        ModelAndView mav = new ModelAndView("addGymLog");
        mav.addObject(new GymSessionForm());

        return mav;
    }

    /**
     * POST User entered Gym Session Data to the server (incl. Validation)
     *
     * @param gymSessionForm
     * @param errors
     *
     * @return
     */
    @RequestMapping(value="/addGymSession", method = RequestMethod.POST)
    public ModelAndView addGymSessionData(@Valid GymSessionForm gymSessionForm, Errors errors) {

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {

            mav.setViewName("addGymLog");

            return mav;
        } else {

            // TODO Obtain the user data from the session (by ID) and add the gymSessionData to the GymUser
            GymLogData gymSessionData = new GymLogData(
                    gymSessionForm.getDate(), gymSessionForm.getDuration(), gymSessionForm.getActivity(),
                    gymSessionForm.getActivityDuration(), gymSessionForm.getDistance(), gymSessionForm.getLevelOrWeight(),
                    gymSessionForm.getCalories(), gymSessionForm.getUserWeight()
            );

            dao.saveUserGymData(gymSessionData);

            try {
                mav.addObject(dao.findAllUserGymData());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return new ModelAndView("redirect:/userLog/show");
    }

    @ModelAttribute("activity")
    public List<String> listActivities() {

        List<String> activity = new ArrayList<>();
        activity.add("");
        activity.add("Running");
        activity.add("Cycling");
        activity.add("Rowing");

        return activity;
    }

    @ModelAttribute("activityDuration")
    public List<String> listActivityDuration() {

        List<String> activityDuration = new ArrayList<>();
        activityDuration.add("");
        activityDuration.add("15");
        activityDuration.add("30");
        activityDuration.add("45");
        activityDuration.add("60");

        return activityDuration;
    }

}
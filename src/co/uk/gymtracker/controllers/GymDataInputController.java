package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymSessionForm;
import co.uk.gymtracker.model.GymUser;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
public class GymDataInputController extends AbstractGymController {

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
    public ModelAndView addGymSessionData(HttpServletRequest request, @Valid GymSessionForm gymSessionForm, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()) {
            mav.setViewName("addGymLog");
            return mav;
        } else {
            // extract the GymUser from the security context
            GymUser user = getLoggedInUser();

            GymLogData gymSessionData = new GymLogData(
                    gymSessionForm.getDate(), gymSessionForm.getDuration(), gymSessionForm.getActivity(),
                    gymSessionForm.getActivityDuration(), gymSessionForm.getDistance(), gymSessionForm.getLevelOrWeight(),
                    gymSessionForm.getCalories(), gymSessionForm.getUserWeight()
            );

            // build and update the list of GymSessions for a user.
            List<GymLogData> gymSessions = new ArrayList<>();

            if(user.getUserSessions() != null) {
                gymSessions = user.getUserSessions();
            }

            gymSessions.add(gymSessionData);

            user.setUserSessions(gymSessions);

            // update the GymUser document
            userDao.updateGymUser(user);

            mav.addObject(gymDataDao.findAllUserGymData());
        }

        // log method performance
        runPerformanceLogging(methodName, watch);

        return new ModelAndView("redirect:/userLog/show");
    }

}
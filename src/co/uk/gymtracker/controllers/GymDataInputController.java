package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymSessionForm;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    @Override
    @RequestMapping(value="/addGymSessionForm")
    public ModelAndView executeEntryPage(ModelAndView mav) {

        logger.entry();

        mav.setViewName("addGymSession");
        mav.addObject(new GymSessionForm());

        logger.exit();

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
    public ModelAndView addGymSessionData(GymSessionForm gymSessionForm, ModelAndView mav, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        logger.entry(gymSessionForm, errors);

        Slf4JStopWatch stopWatch = createStopWatchInstance();

        if(errors.hasErrors()) {
            mav.setViewName("addGymSession");
            return mav;
        } else {
            // extract the GymUser from the security context
            GymUser user = getLoggedInUser();

            // save user submitted gymSessionForm to the database
            gymDataInputService.buildAndSaveGymLogData(user, gymSessionForm);

            // update the model
            mav.addObject(gymDataInputService.retrieveGymLogDataByUserId(user));
        }

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, stopWatch);

        logger.exit();

        return new ModelAndView("redirect:/userLog/show");
    }

}
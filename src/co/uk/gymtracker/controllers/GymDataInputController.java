package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymSessionForm;
import co.uk.gymtracker.validation.GymLogDataValidator;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private GymLogDataValidator gymLogDataValidator;

    @InitBinder("GymLogData")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(gymLogDataValidator);
    }

    /**
     * Displays the addGymSessionForm to the user
     *
     * @return
     */
    @Override
    @RequestMapping(value="/addGymSessionForm")
    public ModelAndView executeEntryPage(ModelAndView mav) {

        logger.entry();

        mav.setViewName("addGymLog");
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
    public ModelAndView addGymSessionData(GymSessionForm gymSessionForm, Errors errors) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        logger.entry(gymSessionForm, errors);

        ModelAndView mav = new ModelAndView();

        gymLogDataValidator.validate(gymSessionForm, errors);

        if(errors.hasErrors()) {
            mav.setViewName("addGymLog");
            return mav;
        } else {
            // extract the GymUser from the security context
            GymUser user = getLoggedInUser();

            GymLogData gymSessionData = new GymLogData(user.getId(),
                    gymSessionForm.getDate(), gymSessionForm.getDuration(), gymSessionForm.getExercise(),
                    gymSessionForm.getActivityDuration(), gymSessionForm.getDistance(), gymSessionForm.getLevel(),
                    gymSessionForm.getWeight(), gymSessionForm.getReps(), gymSessionForm.getCalories(), gymSessionForm.getUserWeight()
            );

            logger.info("added gymSessionData [" + gymSessionData + "]");

            gymDataDao.saveGymLogData(gymSessionData);

            mav.addObject(gymDataDao.findAllUserGymData(user.getId()));
        }

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, new Slf4JStopWatch());

        logger.exit();

        return new ModelAndView("redirect:/userLog/show");
    }

}
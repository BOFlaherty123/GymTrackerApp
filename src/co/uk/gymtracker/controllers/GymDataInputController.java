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

    private static final String CARDIO_EXERCISE = "CE";
    private static final String WEIGHT_EXERCISE = "WE";

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

        gymLogDataValidator.validate(gymSessionForm, errors);

        if(errors.hasErrors()) {
            mav.setViewName("addGymSession");
            return mav;
        } else {
            // extract the GymUser from the security context
            GymUser user = getLoggedInUser();

            GymLogData gymSessionData = populateGymLogData(gymSessionForm, user);

            String typeOfExercise = gymSessionForm.getTypeOfExercise();
            logger.info(".getTypeOfExercise() [" + typeOfExercise + "]");

            boolean saveCardioExercise; boolean saveWeightExercise;
            saveCardioExercise = isCardioExercise(typeOfExercise);
            saveWeightExercise = isWeightExercise(typeOfExercise);

            if(!typeOfExercise.equals(CARDIO_EXERCISE) && !typeOfExercise.equals(WEIGHT_EXERCISE)){
                saveCardioExercise = true;
                saveWeightExercise = true;
            }

            addCardioExercise(gymSessionForm, gymSessionData, saveCardioExercise);
            addWeightExercise(gymSessionForm, gymSessionData, saveWeightExercise);

            logger.info("added gymSessionData [" + gymSessionData + "]");

            gymDataDao.saveGymLogData(gymSessionData);
            logger.info("saved gymSessionData [" + gymSessionData + "]");

            mav.addObject(gymDataDao.findAllUserGymData(user.getId()));
        }

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, new Slf4JStopWatch());

        logger.exit();

        return new ModelAndView("redirect:/userLog/show");
    }

    private GymLogData populateGymLogData(GymSessionForm gymSessionForm, GymUser user) {
        GymLogData gymSessionData = new GymLogData();

        gymSessionData.setUserId(user.getId());
        gymSessionData.setDate(gymSessionForm.getDate());
        gymSessionData.setDuration(gymSessionForm.getDuration());
        gymSessionData.setUserWeight(gymSessionForm.getUserWeight());

        return gymSessionData;
    }

    private boolean isWeightExercise(String typeOfExercise) {
        return (typeOfExercise.equals(WEIGHT_EXERCISE)) ? true : false;
    }

    private boolean isCardioExercise(String typeOfExercise) {
        return (typeOfExercise.equals(CARDIO_EXERCISE)) ? true : false;
    }

    private void addWeightExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveWeightExercise) {
        if(saveWeightExercise) {
            gymSessionData.setExerciseWeight(gymSessionForm.getExerciseWeight());
        }
    }

    private void addCardioExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveCardioExercise) {
        if(saveCardioExercise) {
            gymSessionData.setExerciseCardio(gymSessionForm.getExerciseCardio());
        }
    }

}
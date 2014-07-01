package co.uk.gymtracker.controllers;

import co.uk.gymtracker.exceptions.AppRunTimeException;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymLogSearch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;

/**
 * GymUserLogController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Controller
@RequestMapping(value="/userLog")
public class GymUserLogController extends AbstractGymController {

    /**
     * Setup and displays the GymSessionLog page
     *
     * @return
     */
    @Override
    @RequestMapping(value="/show")
    public ModelAndView executeEntryPage(ModelAndView mav) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        // spring Convention over Configuration
        mav.setViewName("userLog");

        GymUser user = retrieveGymUser();

        if(user != null) {
            logger.info(format("[ %s ] - [ %s ] has gym sessions.", methodName, user.getUsername()));

            List<GymLogData> gymRecords = gymDataInputService.retrieveGymLogDataByUserId(user);

            // spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
            mav.addObject(gymRecords);
            logger.info(format("[ %s ] - [ %s ] gym records added to the model.", methodName, gymRecords.size()));
        } else {
            throw new AppRunTimeException(".retrieveGymUser() has returned a null GymUser object");
        }

        mav.addObject(new GymLogSearch());

        return mav;
    }

    /**
     * Search for gym sessions using the GymLogSearch form object.
     *
     * @param gymLogSearch
     * @param error
     * @return
     */
    @RequestMapping(value="/search")
    public ModelAndView executeGymSessionsSearch(@Valid GymLogSearch gymLogSearch, Errors error) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        Slf4JStopWatch stopWatch = createStopWatchInstance();

        ModelAndView mav = new ModelAndView("userLog");

        if(error.hasErrors()) {
            logger.info(format("[ %s ] .hasErrors() - %s", methodName, gymLogSearch.toString()));
            return mav;
        } else {
            List<GymLogData> gymRecords = gymDataInputService.retrieveGymUserDataByActivity(retrieveGymUser(), gymLogSearch.getCardioExercise());
            mav.addObject(gymRecords);

            logger.info(format("[ %s ] - [ %s ] gym records added to the model.", methodName, gymRecords.size()));
        }

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, stopWatch);

        return mav;
    }

    private GymUser retrieveGymUser() {
        return getLoggedInUser();
    }

}
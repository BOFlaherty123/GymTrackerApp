package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymLogSearch;
import org.perf4j.StopWatch;
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
    public ModelAndView processEntryPage(ModelAndView mav) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        // spring Convention over Configuration
        mav.setViewName("userLog");

        GymUser user = retrieveGymUser();

        if(user.getUserSessions() != null) {
            logger.info(format("%s - %s has gym sessions.", methodName, user.getUsername()));

            List<GymLogData> gymRecords = user.getUserSessions();

            // spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
            mav.addObject(gymRecords);
            logger.info(format("%s - %s gym records added to the model.", methodName, gymRecords.size()));

        }

        mav.addObject(new GymLogSearch());

        return mav;
    }

    /**
     * Search for guym sessions using the GymLogSearch form object.
     *
     * @param gymLogSearch
     * @param error
     * @return
     */
    @RequestMapping(value="/search")
    public ModelAndView displayAdminConsoleMessage(@Valid GymLogSearch gymLogSearch, Errors error) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

        GymUser user = retrieveGymUser();

        ModelAndView mav = new ModelAndView("userLog");

        if(error.hasErrors()) {
            logger.info(format("%s .hasErrors() - %s", methodName, gymLogSearch.toString()));
            return mav;
        } else {

            List<GymLogData> gymRecords = gymDataDao.findGymUserDataByActivity(user, gymLogSearch.getActivity());
            mav.addObject(gymRecords);
            logger.info(format("%s - %s gym records added to the model.", methodName, gymRecords.size()));

        }

        // log method performance
        runPerformanceLogging(this.getClass().getName(), methodName, watch);

        return mav;
    }

    private GymUser retrieveGymUser() {
        return getLoggedInUser();
    }

}
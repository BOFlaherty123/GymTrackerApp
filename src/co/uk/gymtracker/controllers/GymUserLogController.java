package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    private static final Logger LOG = LoggerFactory.getLogger(GymUserLogController.class);

    /**
     * Setup and displays the GymSessionLog page
     *
     * @return
     */
    @RequestMapping(value="/show")
    public ModelAndView displayLog() {

        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        // spring Convention over Configuration
        ModelAndView mav = new ModelAndView("userLog");

        GymUser user = getLoggedInUser();

        if(user.getUserSessions() != null) {
            LOG.info(format("%s - %s has gym sessions.", methodName, user.getUsername()));

            List<GymLogData> gymRecords = user.getUserSessions();

            // spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
            mav.addObject(gymRecords);

            LOG.info(format("%s - %s gym records added to the model.", methodName, gymRecords.size()));
        }

        return mav;
    }

    @RequestMapping(value="/admin")
    public void displayAdminConsoleMessage() {
        System.out.println("Admin Console Message");
    }

}
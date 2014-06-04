package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping(value="/show")
    public ModelAndView displayLog(HttpServletRequest request) {

        // spring Convention over Configuration
        ModelAndView mav = new ModelAndView("userLog");

        GymUser user = getLoggedInUser();

        if(user.getUserSessions() != null) {
            List<GymLogData> gymRecords = user.getUserSessions();

            // spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
            mav.addObject(gymRecords);
        }

        return mav;
    }

    @RequestMapping(value="/admin")
    public void displayAdminConsoleMessage() {
        System.out.println("Admin Console Message");
    }

}
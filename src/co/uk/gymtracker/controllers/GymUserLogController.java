package co.uk.gymtracker.controllers;

import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

        // Spring Convention over Configuration
        ModelAndView mav = new ModelAndView("userLog");

        SecurityContext ctx = SecurityContextHolder.getContext();

        GymUser user = userDao.findGymUser(ctx.getAuthentication().getName());

        if(user.getUserSessions() != null) {
            List<GymLogData> gymRecords = user.getUserSessions();

            // Spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
            mav.addObject(gymRecords);
        }

        return mav;
    }

    @RequestMapping(value="/admin")
    public void displayAdminConsoleMessage() {
        System.out.println("Admin Console Message");
    }

}
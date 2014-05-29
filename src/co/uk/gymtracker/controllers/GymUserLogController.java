package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
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
public class GymUserLogController {

    @Autowired
    private GymUserDataDao dao;

    @Autowired
    private GymUserDao userDao;

    @RequestMapping(value="/session/{username}", method = RequestMethod.POST)
    public void storeUsernameInSession(@PathVariable("username") String username, HttpServletRequest request) {

        GymUser user = userDao.findGymUser(username);
        request.getSession().setAttribute("sessionUser", user);

    }

    /**
     * Setup and displays the GymSessionLog page
     *
     * @return
     */
    @RequestMapping(value="/show")
    public ModelAndView displayLog() {

        // Spring Convention over Configuration
        ModelAndView mav = new ModelAndView("userLog");

        List<GymLogData> gymRecords = null;
        try {
            gymRecords = dao.findAllUserGymData();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Spring Convention Over Configuration Example (List is called within the jsp via gymLogDataList)
        mav.addObject(gymRecords);

        return mav;
    }


    @RequestMapping(value="/admin")
    public void displayAdminConsoleMessage() {
        System.out.println("Admin Console Message");
    }

}
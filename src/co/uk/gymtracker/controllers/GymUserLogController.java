package co.uk.gymtracker.controllers;

import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.model.GymLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value="/show")
    public String displayLog(ModelMap model) {

        GymLogData gymRecord = new GymLogData("01/01/1970", "1h", "Cycling", "1h", "9", "10st 10lbs");
        dao.saveUserGymData(gymRecord);

        List<GymLogData> gymRecords = dao.findAllUserGymData();
        System.out.println("gymRecords.size() " + gymRecords.size());

        model.addAttribute("gymRecords", gymRecords);

        return "userLog";
    }

}

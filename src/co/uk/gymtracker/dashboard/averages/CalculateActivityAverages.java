package co.uk.gymtracker.dashboard.averages;

import co.uk.gymtracker.controllers.AbstractGymController;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.service.GymUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 22/05/14
 * @project GymTrackerApp
 */
@Component
public class CalculateActivityAverages extends AbstractGymController {

    private static final String ACTIVITY_RUNNING = "Running";
    private static final String ACTIVITY_CYCLING = "Cycling";

    @Autowired
    public GymUserService userService;

    /**
     * Calculate the Avg Distance per Activity
     *
     * @param gymUser
     * @return
     */
    @RequestMapping(value="/calculateActivityDurationAverages", method = RequestMethod.GET)
    public List<ActivityAverage> calculateActivityAverages(GymUser gymUser) {

        List<ActivityAverage> averages = new ArrayList<>();

        // Get all gym sessions by a particular user.
        List<GymLogData> userGymSessions = gymUser.getUserSessions();

        // Calculate the total of 'Distance' and divide by the number of sessions
        gymDataDao.findGymUserDataByActivity(gymUser);

        // Add result(s) to ActivityAverages List and return to the Controller
        return new ArrayList<ActivityAverage>();
    }

}

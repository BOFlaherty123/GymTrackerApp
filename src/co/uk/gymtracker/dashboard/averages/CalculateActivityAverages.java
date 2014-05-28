package co.uk.gymtracker.dashboard.averages;

import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.service.GymUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class CalculateActivityAverages {

    @Autowired
    public GymUserService userService;

    /**
     * Calculate the Avg Distance per Activity
     *
     * @param gymUser
     * @return
     */
      public List<ActivityAverage> calculateActivityAverages(GymUser gymUser) {

         // Get all gym sessions by a particular user.
         List<GymLogData> userGymSessions = gymUser.getUserSessions();

         // Filter search by 'Activity'


         // Calculate the total of 'Distance' and divide by the number of sessions


         // Add result(s) to ActivityAverages List and return to the Controller

          return new ArrayList<ActivityAverage>();
      }


}

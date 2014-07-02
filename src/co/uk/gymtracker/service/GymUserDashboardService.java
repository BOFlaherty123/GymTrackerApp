package co.uk.gymtracker.service;

import co.uk.gymtracker.dashboard.averages.CalculateActivityAverages;
import co.uk.gymtracker.dashboard.targets.CalculateUserTargets;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.dashboard.ActivityAverage;
import co.uk.gymtracker.model.dashboard.TargetIncrease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * Service containing business logic for the GymUserDashboardController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
@Service
public class GymUserDashboardService extends AbstractGymService {

    private static final String DISTANCE = "Distance";
    private static final String DURATION = "Duration";
    private static final String ACTIVITY_RUNNING = "Running";
    private static final String ACTIVITY_CYCLING = "Cycling";

    @Autowired
    public CalculateActivityAverages calculateAverages;

    @Autowired
    public CalculateUserTargets targets;


    /**
     *  calculates and returns the averages for each exercise for a user
     *
     * @param user
     * @return
     */
    public Map<String, String> processUserAverages(GymUser user) {

        logger.entry(user);

        Map<String,String> distanceAverages = new HashMap<>();
        
        List<ActivityAverage> averages = calculateAverages.calculateActivityAverages(user);

        for(ActivityAverage avg : averages) {
            logger.info(format("processing averages for activity: %s.", avg.getActivity()));

            Map<String, String> actDistanceAvg = processActivityAverageDistances(avg);
            String activity = avg.getActivity();

            String distanceAvg = (activity.equals(ACTIVITY_RUNNING)) ? distanceAverages.put("running_avg_distance", actDistanceAvg.get(activity)) :
                    (activity.equals(ACTIVITY_CYCLING)) ? distanceAverages.put("cycling_avg_distance", actDistanceAvg.get(activity)) :
                            distanceAverages.put("rowing_avg_distance", actDistanceAvg.get(activity));

            logger.info(format("[ %s ] added to distanceAverages", distanceAvg));
        }

        user.setActivityAverages(averages);
        dao.updateGymUser(user);

        logger.exit();

        return distanceAverages;
    }

    /**
     * calculates and returns the average distances per exercise for a user
     *
     * @param avg
     * @return
     */
    private Map<String,String> processActivityAverageDistances(ActivityAverage avg) {
        String activity = avg.getActivity();
        String distance = avg.getAverageDistance();
        logger.info(format("activity average distance: %s.", distance));

        Map<String, String> averages = new HashMap<>();
        averages.put(activity, distance);

        logger.info("Activity distance added for [" + activity + "]");

        return averages;
    }


    /**
     * calculates the percentage value for the duration of each activity
     *
     * @param user
     * @return
     */
    public Map<String, String> processActivityDurationPercentages(GymUser user) {

        Map<String, BigDecimal> durationPercentages = calculateAverages.calculateAvgDurationPercentages(user);
        Map<String, String> percentages = new HashMap<>();

        for(Map.Entry<String, BigDecimal> entry : durationPercentages.entrySet()) {
            percentages.put(entry.getKey().toLowerCase() + "_duration_percent", entry.getValue().toString());
        }

        return percentages;
    }

    /**
     *  calculates and returns the user target increases for
     *
     * @param gymUser
     * @param activity
     * @param percentage
     * @return
     */
    public Map<String, String> processUserTargetIncreases(GymUser gymUser, String activity, int percentage) {

        // TODO - Change from return value of String to TargetIncrease (as a JSON obj? Jackson will be required)
        TargetIncrease targetIncrease = targets.calculateTargetOnPercentageIncrease(gymUser, activity, percentage);

        Map<String,String> targetIncreases = new HashMap<>();
        targetIncreases.put(DISTANCE, targetIncrease.getDistanceIncrease());
        targetIncreases.put(DURATION, targetIncrease.getDurationIncrease());

        return targetIncreases;
    }

}

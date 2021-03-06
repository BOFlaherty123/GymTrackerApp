package co.uk.gymtracker.dashboard.averages;

import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.dashboard.ActivityAverage;
import co.uk.gymtracker.service.GymUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CalculateActivityAverages
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 22/05/14
 * @project GymTrackerApp
 */
@Component
public class CalculateActivityAverages  {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private static final String ZERO_VALUE = "0";
    private static final String DISTANCE = "Distance";
    private static final String DURATION = "Duration";

    @Autowired
    public GymUserLoginService userService;

    @Autowired
    public GymUserDataDao gymDataDao;

    /**
     * Calculate the Avg Distance per Activity
     *
     * @param gymUser
     * @return
     */
    @RequestMapping(value="/calculateActivityDurationAverages", method = RequestMethod.GET)
    public List<ActivityAverage> calculateActivityAverages(GymUser gymUser) {

        List<ActivityAverage> activityAverages = new ArrayList<>();

        // TODO - Store list of Activities in the database
        List<String> gymActivities = buildActivityList();

        for(String activity : gymActivities) {
            // retrieve gym session data for
            List<GymLogData> userSessionData = gymDataDao.findGymUserDataByActivity(gymUser, activity);

            if(!userSessionData.isEmpty()) {
                activityAverages.add(calculateAverages(userSessionData, activity));
            }
        }

        // add result(s) to ActivityAverages List and return to the Controller
        return activityAverages;
    }

    /**
     * Calculates and builds the ActivityAverages object for each Activity
     *
     * @param activitySessionData
     * @param activity
     * @return
     */
    private ActivityAverage calculateAverages(List<GymLogData> activitySessionData, String activity) {

        BigDecimal totalDistance = new BigDecimal(ZERO_VALUE);  BigDecimal totalDuration = new BigDecimal(ZERO_VALUE);

        // calculate total distance
        for(GymLogData gymLogData : activitySessionData)  {

            for(ExerciseCardio cardioExercise : gymLogData.getExerciseCardio()) {
                totalDistance = addValue(totalDistance, cardioExercise.getDistance());
                totalDuration = addValue(totalDuration, cardioExercise.getDuration());
            }
        }

        // retrieve the total number of sessions for the activity
        BigDecimal totalSessions = parseNumberOfGymSessions(activitySessionData.size());

        // calculate Averages
        BigDecimal averageDistance = calculateAverage(totalDistance, totalSessions);
        BigDecimal averageDuration = calculateAverage(totalDuration, totalSessions);

        // create a map containing the activity totals for use with further calculations
        Map<String,String> activityTotals = new HashMap<>();
        activityTotals.put(DISTANCE, String.valueOf(totalDistance));
        activityTotals.put(DURATION, String.valueOf(totalDuration));

        return buildActivityAverage(activity, String.valueOf(totalSessions), averageDistance, averageDuration, activityTotals);
    }

    private BigDecimal addValue(BigDecimal total, String value) {
        return total.add(new BigDecimal(value));
    }

    /**
     * calculate the total average by dividing by the total number of activity sessions
     *
     * @param total
     * @param totalSessions
     * @return
     */
    private BigDecimal calculateAverage(BigDecimal total, BigDecimal totalSessions) {
        return total.divide(totalSessions, RoundingMode.HALF_UP);
    }

    /**
     *  Converts the integer number of gym sessions per activity into a BigDecimal value
     *
     * @param noOfSessions
     * @return
     */
    private BigDecimal parseNumberOfGymSessions(int noOfSessions) {
        return new BigDecimal(String.valueOf(noOfSessions));
    }

    /**
     * Builds an ActivityAverage for each activity completed by a user
     *
     * @param activity
     * @param numberOfSessions
     * @param averageDistance
     * @param averageDuration
     * @return
     */
    private ActivityAverage buildActivityAverage(String activity,
                                                 String numberOfSessions, BigDecimal averageDistance,
                                                 BigDecimal averageDuration, Map<String, String> activityTotals) {

        return new ActivityAverage(activity, numberOfSessions, String.valueOf(averageDistance),
                String.valueOf(averageDuration), activityTotals);
    }

    /**
     *
     * @param gymUser
     * @return
     */
    public Map<String, BigDecimal> calculateAvgDurationPercentages(GymUser gymUser) {

        Map<String, BigDecimal> durations = new HashMap<>();

        List<ActivityAverage> averages = calculateActivityAverages(gymUser);
        for(ActivityAverage avg : averages) {
            durations.put(avg.getActivity(), new BigDecimal(avg.getActivityTotals().get("duration")));
        }

        BigDecimal activityTotal = new BigDecimal(ZERO_VALUE);
        // accumulate the activity duration total
        activityTotal = addValueToActivityDurationTotal(durations, activityTotal);

        // calculate the value to divide each activity total by
        BigDecimal pieDivisible = activityTotal.divide(ONE_HUNDRED).setScale(2, RoundingMode.CEILING);

        // add values to model
        durations = buildAvgDurationPercentageMap(durations, pieDivisible);

        return durations;
    }

    /**
     *
     * @param durations
     * @param activityTotal
     * @return
     */
    private BigDecimal addValueToActivityDurationTotal(Map<String, BigDecimal> durations, BigDecimal activityTotal) {

        for(Map.Entry<String, BigDecimal> entry : durations.entrySet()) {
            activityTotal = activityTotal.add(entry.getValue());
        }

        return activityTotal;
    }

    /**
     *
     * @param durations
     * @param pieDivisible
     * @return
     */
    private Map<String, BigDecimal> buildAvgDurationPercentageMap(Map<String, BigDecimal> durations, BigDecimal pieDivisible) {

        for(Map.Entry<String, BigDecimal> entry : durations.entrySet()) {
            entry.setValue(entry.getValue().divide(pieDivisible, RoundingMode.CEILING).setScale(2));
        }

        return durations;
    }

    private List<String> buildActivityList() {
        List<String> gymActivities = new ArrayList<>();
        gymActivities.add("Running");
        gymActivities.add("Cycling");
        gymActivities.add("Rowing");

        return gymActivities;
    }

}
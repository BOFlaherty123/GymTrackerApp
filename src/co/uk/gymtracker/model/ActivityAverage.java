package co.uk.gymtracker.model;

import java.util.Map;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 22/05/14
 * @project GymTrackerApp
 */
public class ActivityAverage {

    private final String activity;
    private final String numberOfSessions;
    private final String averageDistance;
    private final String averageDuration;

    private final Map<String, String> activityTotals;

    public ActivityAverage(String activity_Name,  String number_Of_Sessions,
                           String average_Distance, String average_Duration, Map<String, String> activity_totals) {

        activity = activity_Name;
        numberOfSessions = number_Of_Sessions;
        averageDistance = average_Distance;
        averageDuration = average_Duration;
        activityTotals = activity_totals;
    }

    public String getActivity() {
        return activity;
    }

    public String getNumberOfSessions() {
        return numberOfSessions;
    }

    public String getAverageDistance() {
        return averageDistance;
    }

    public String getAverageDuration() {
        return averageDuration;
    }

    public Map<String, String> getActivityTotals() {
        return activityTotals;
    }
}

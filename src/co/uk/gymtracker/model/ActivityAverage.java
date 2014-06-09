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

    private String activity;
    private String numberOfSessions;
    private String averageDistance;
    private String averageDuration;
    private Map<String, String> activityTotals;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(String numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public String getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(String averageDistance) {
        this.averageDistance = averageDistance;
    }

    public String getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(String averageDuration) {
        this.averageDuration = averageDuration;
    }

    public Map<String, String> getActivityTotals() {
        return activityTotals;
    }

    public void setActivityTotals(Map<String, String> activityTotals) {
        this.activityTotals = activityTotals;
    }
}

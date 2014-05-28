package co.uk.gymtracker.model;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 22/05/14
 * @project GymTrackerApp
 */
public class ActivityAverage {

    private String activity;
    private String totalDistance;
    private String numberOfSessions;
    private String averageDistance;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
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
}

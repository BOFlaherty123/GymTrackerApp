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
    private String averageDuration;

    public ActivityAverage(String activity_Name, String total_Distance, String number_Of_Sessions,
                           String average_Distance, String average_Duration) {

        activity = activity_Name;
        totalDistance = total_Distance;
        numberOfSessions = number_Of_Sessions;
        averageDistance = average_Distance;
        averageDuration = average_Duration;
    }

    public String getActivity() {
        return activity;
    }

    public String getTotalDistance() {
        return totalDistance;
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
}

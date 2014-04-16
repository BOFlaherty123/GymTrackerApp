package co.uk.gymtracker.model;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
public class GymLogData {

    private String date;
    private String duration;
    private String activity;
    private String activityDuration;
    private String levelOrWeight;
    private String userWeight;

    public GymLogData(String date, String duration, String activity, String activityDuration,
                      String levelOrWeight, String userWeight) {

        this.date = date;
        this.duration = duration;
        this.activity = activity;
        this.activityDuration = activityDuration;
        this.levelOrWeight = levelOrWeight;
        this.userWeight = userWeight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(String activityDuration) {
        this.activityDuration = activityDuration;
    }

    public String getLevelOrWeight() {
        return levelOrWeight;
    }

    public void setLevelOrWeight(String levelOrWeight) {
        this.levelOrWeight = levelOrWeight;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }
}

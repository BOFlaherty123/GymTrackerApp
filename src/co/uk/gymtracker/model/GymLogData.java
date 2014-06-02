package co.uk.gymtracker.model;

import java.io.Serializable;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
public class GymLogData implements Serializable {

    private String date;
    private String duration;
    private String activity;
    private String activityDuration;
    private String distance;
    private String levelOrWeight;
    private String calories;
    private String userWeight;

    public GymLogData(String date, String duration, String activity, String activityDuration,
                      String distance, String levelOrWeight, String calories, String userWeight) {

        this.date = date;
        this.duration = duration;
        this.activity = activity;
        this.activityDuration = activityDuration;
        this.distance = distance;
        this.levelOrWeight = levelOrWeight;
        this.calories = calories;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String toString() {
        return "Date: " + date + " Duration: " + duration + " Activity: " + activity  + " ActivityDuration: " + activityDuration
                + "Distance: " + distance + " LevelOrWeight: " + levelOrWeight + "Calories: " + calories + "UserWeight: " + userWeight;
    }

}

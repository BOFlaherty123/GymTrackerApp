package co.uk.gymtracker.model.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 24/04/14
 * @project GymTrackerApp
 */
public class GymSessionForm {

    @NotEmpty(message = "Date must not be empty")
    private String date;
    @NotEmpty(message = "Duration must not be empty")
    private String duration;
    @NotEmpty(message = "Activity must not be empty")
    private String activity;
    @NotEmpty(message = "Activity Duration must not be empty")
    private String activityDuration;
    @NotEmpty(message = "Distance must not be empty")
    private String distance;
    @NotEmpty(message = "Level/Weight must not be empty")
    private String levelOrWeight;
    @NotEmpty(message = "Calories must not be empty")
    private String calories;
    @NotEmpty(message = "User Weight must not be empty")
    private String userWeight;

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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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
}

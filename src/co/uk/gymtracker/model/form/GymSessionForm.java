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
    private String cardioExercise;
    private String activityDuration;
    private String distance;
    private String level;
    private String weightMachine;
    private String weight;
    private String reps;
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

    public String getCardioExercise() {
        return cardioExercise;
    }

    public void setCardioExercise(String cardioExercise) {
        this.cardioExercise = cardioExercise;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getWeightMachine() {
        return weightMachine;
    }

    public void setWeightMachine(String weightMachine) {
        this.weightMachine = weightMachine;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
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

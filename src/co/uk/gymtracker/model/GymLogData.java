package co.uk.gymtracker.model;

import java.io.Serializable;

/**
 * Gym Session Log Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
public class GymLogData implements Serializable {

    private String date;
    private String duration;
    private String cardioExercise;
    private String activityDuration;
    private String distance;
    private String level;
    private String weight;
    private String reps;
    private String calories;
    private String userWeight;
    private String exercise;

    public GymLogData(String date, String duration, String cardioExercise, String activityDuration,
                      String distance, String level, String weight, String reps, String calories, String userWeight,
                      String exercise) {

        this.date = date;
        this.duration = duration;
        this.cardioExercise = cardioExercise;
        this.activityDuration = activityDuration;
        this.distance = distance;
        this.level = level;
        this.weight = weight;
        this.reps = reps;
        this.calories = calories;
        this.userWeight = userWeight;
        this.exercise = exercise;
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

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "GymLogData{" +
                "date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", cardioExercise='" + cardioExercise + '\'' +
                ", activityDuration='" + activityDuration + '\'' +
                ", distance='" + distance + '\'' +
                ", level='" + level + '\'' +
                ", weight='" + weight + '\'' +
                ", reps='" + reps + '\'' +
                ", calories='" + calories + '\'' +
                ", userWeight='" + userWeight + '\'' +
                ", exercise='" + exercise + '\'' +
                '}';
    }
}


package co.uk.gymtracker.model;

/**
 * Cardio Exercise Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 24/06/14
 * @project GymTrackerApp
 */
public class ExerciseCardio {

    private String exercise;
    private String duration;
    private String distance;
    private String level;
    private String calories;

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "ExerciseCardio{" +
                "exercise='" + exercise + '\'' +
                ", duration='" + duration + '\'' +
                ", distance='" + distance + '\'' +
                ", level='" + level + '\'' +
                ", calories='" + calories + '\'' +
                '}';
    }
}

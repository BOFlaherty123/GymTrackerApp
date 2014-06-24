package co.uk.gymtracker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Gym Session Log Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Document
public class GymLogData implements Serializable {

    private String id;
    private String userId;
    private String date;
    private String duration;
    private String userWeight;
    private String typeOfExercise;
    private List<ExerciseCardio> exerciseCardio;
    private List<ExerciseWeight> exerciseWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public List<ExerciseCardio> getExerciseCardio() {
        return exerciseCardio;
    }

    public void setExerciseCardio(List<ExerciseCardio> exerciseCardio) {
        this.exerciseCardio = exerciseCardio;
    }

    public List<ExerciseWeight> getExerciseWeight() {
        return exerciseWeight;
    }

    public void setExerciseWeight(List<ExerciseWeight> exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }

    @Override
    public String toString() {
        return "GymLogData{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", userWeight='" + userWeight + '\'' +
                '}';
    }
}


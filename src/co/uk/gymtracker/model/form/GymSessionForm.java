package co.uk.gymtracker.model.form;

import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.ExerciseWeight;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

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
    private String typeOfExercise;
    @NotEmpty(message = "User Weight must not be empty")
    private String userWeight;
    private List<ExerciseCardio> exerciseCardio;
    private List<ExerciseWeight> exerciseWeight;

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

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
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
        return "GymSessionForm{" +
                "date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", typeOfExercise='" + typeOfExercise + '\'' +
                ", userWeight='" + userWeight + '\'' +
                ", exerciseCardio=" + exerciseCardio +
                ", exerciseWeight=" + exerciseWeight +
                '}';
    }
}

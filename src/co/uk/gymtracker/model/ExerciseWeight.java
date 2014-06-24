package co.uk.gymtracker.model;

/**
 * Weight Exercise Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 24/06/14
 * @project GymTrackerApp
 */
public class ExerciseWeight {

    private String exercise;
    private String reps;
    private String weightLifted;

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeightLifted() {
        return weightLifted;
    }

    public void setWeightLifted(String weightLifted) {
        this.weightLifted = weightLifted;
    }

    @Override
    public String toString() {
        return "ExerciseWeight{" +
                "exercise='" + exercise + '\'' +
                ", reps='" + reps + '\'' +
                ", weightLifted='" + weightLifted + '\'' +
                '}';
    }

}

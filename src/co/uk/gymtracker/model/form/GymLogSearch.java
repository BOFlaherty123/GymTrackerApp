package co.uk.gymtracker.model.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/06/2014
 * @project GymTrackerApp
 */
public class GymLogSearch {

    @NotEmpty(message="An Exercise is required.")
    private String cardioExercise;
    private String startDate;
    private String endDate;

    public String getCardioExercise() {
        return cardioExercise;
    }

    public void setCardioExercise(String cardioExercise) {
        this.cardioExercise = cardioExercise;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String toString() {
        return "cardioExercise[ " + cardioExercise + " ] startDate [ " + startDate + " ] endDate [ " + endDate + " ]";
    }

}

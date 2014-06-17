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

    @NotEmpty(message="An Activity is required.")
    private String activity;
    private String startDate;
    private String endDate;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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
        return "activity[ " + activity + " ] startDate [ " + startDate + " ] endDate [ " + endDate + " ]";
    }

}

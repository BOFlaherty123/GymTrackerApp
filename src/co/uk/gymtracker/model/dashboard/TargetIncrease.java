package co.uk.gymtracker.model.dashboard;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 05/06/14
 * @project GymTrackerApp
 */
public class TargetIncrease {

    private String Activity;
    private String percentageIncrease;
    private String durationIncrease;
    private String distanceIncrease;
    private String caloriesIncrease;

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public String getPercentageIncrease() {
        return percentageIncrease;
    }

    public void setPercentageIncrease(String percentageIncrease) {
        this.percentageIncrease = percentageIncrease;
    }

    public String getDurationIncrease() {
        return durationIncrease;
    }

    public void setDurationIncrease(String durationIncrease) {
        this.durationIncrease = durationIncrease;
    }

    public String getDistanceIncrease() {
        return distanceIncrease;
    }

    public void setDistanceIncrease(String distanceIncrease) {
        this.distanceIncrease = distanceIncrease;
    }

    public String getCaloriesIncrease() {
        return caloriesIncrease;
    }

    public void setCaloriesIncrease(String caloriesIncrease) {
        this.caloriesIncrease = caloriesIncrease;
    }
}

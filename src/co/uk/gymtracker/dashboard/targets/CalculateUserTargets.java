package co.uk.gymtracker.dashboard.targets;

import co.uk.gymtracker.logging.PerformanceLogging;
import co.uk.gymtracker.model.dashboard.ActivityAverage;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.dashboard.TargetIncrease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
 * CalculateUserTargets
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/04/14
 * @project GymTrackerApp
 */
@Component
public class CalculateUserTargets {

    private static final String ONE_HUNDRED = "100";

    @Autowired
    private PerformanceLogging performanceLogging;

    /**
     * based on the percentage increase selected, calculate the target distance/duration/calories for a user
     *
     * @param gymuser
     * @param activity
     * @param percentage
     * @return
     */
    public TargetIncrease calculateTargetOnPercentageIncrease(GymUser gymuser, String activity, int percentage) {

        TargetIncrease target = new TargetIncrease();
        target.setActivity(activity);
        target.setPercentageIncrease(String.valueOf(percentage));

        List<ActivityAverage> averages = gymuser.getActivityAverages();


        for(ActivityAverage average : averages) {

            if(average.getActivity().equals(activity)) {
                calculatePercentageDurationIncreased(average.getAverageDuration(), target);
                calculatePercentageDistanceIncreased(average.getAverageDistance(), target);
            }
        }

        return target;
    }

    private TargetIncrease calculatePercentageDurationIncreased(String duration, TargetIncrease target) {

        BigDecimal increase = new BigDecimal(duration);
        increase = increase.add(increase.divide(new BigDecimal(ONE_HUNDRED)).multiply(new BigDecimal(target.getPercentageIncrease())));

        target.setDurationIncrease(String.valueOf(increase));

        return target;
    }

    private TargetIncrease calculatePercentageDistanceIncreased(String distance, TargetIncrease target) {

        BigDecimal increase = new BigDecimal(distance);
        increase = increase.add(increase.divide(new BigDecimal(ONE_HUNDRED)).multiply(new BigDecimal(target.getPercentageIncrease())));

        target.setDistanceIncrease(String.valueOf(increase));

        return target;

    }

}

package co.uk.gymtracker.dashboard.targets;

import co.uk.gymtracker.logging.PerformanceLogging;
import co.uk.gymtracker.model.ActivityAverage;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.TargetIncrease;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
 * Description Here
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
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        StopWatch watch = new Slf4JStopWatch();

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

        performanceLogging.isMethodProcessingBelowThreshold(methodName, watch);

        return target;
    }

    private TargetIncrease calculatePercentageDurationIncreased(String duration, TargetIncrease target) {

        BigDecimal increase = new BigDecimal(duration);
        increase = increase.add(increase.divide(new BigDecimal(ONE_HUNDRED)).multiply(new BigDecimal(target.getPercentageIncrease())));

        target.setDurationIncrease(increase.toString());

        return target;
    }

    private TargetIncrease calculatePercentageDistanceIncreased(String distance, TargetIncrease target) {

        BigDecimal increase = new BigDecimal(distance);
        increase = increase.add(increase.divide(new BigDecimal(ONE_HUNDRED)).multiply(new BigDecimal(target.getPercentageIncrease())));

        target.setDistanceIncrease(increase.toString());

        return target;

    }

}

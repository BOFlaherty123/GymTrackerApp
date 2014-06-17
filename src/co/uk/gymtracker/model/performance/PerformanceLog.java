package co.uk.gymtracker.model.performance;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/06/2014
 * @project GymTrackerApp
 */
public class PerformanceLog {

    String methodName;
    long elapsedTime;

    public PerformanceLog(String method, long processingTime) {
        methodName = method;
        elapsedTime = processingTime;
    }

}

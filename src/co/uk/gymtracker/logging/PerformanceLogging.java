package co.uk.gymtracker.logging;

import org.perf4j.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Performance logging component to monitor the performance of methods within the web application.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 09/06/2014
 * @project GymTrackerApp
 */
@Component
public class PerformanceLogging {

    private static final long ONE_SECOND = 100;

    private static final Logger logger = LoggerFactory.getLogger(PerformanceLogging.class);

    /**
     * logs and stores a record in the database if a method takes longer than 1 second to complete it's process
     *
     * @param methodName
     * @param watch
     */
    public void isMethodProcessingBelowThreshold(String methodName, StopWatch watch) {

        // if method processing elapsedTime is 1 second or over, log warning for following up.
        if(watch.getElapsedTime() >= ONE_SECOND) {
            logger.warn(format("%s method - slow performance (%s ms) review.", methodName, watch.getElapsedTime()));

            // TODO - log slow method performance to the database for review.
        }

        logger.info(format("%s -  method performance %s", methodName, watch.stop()));

    }

}

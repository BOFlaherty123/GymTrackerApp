package co.uk.gymtracker.logging;

import co.uk.gymtracker.dao.AppPerformanceDao;
import co.uk.gymtracker.model.performance.PerformanceLog;
import org.perf4j.StopWatch;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AppPerformanceDao appPerformanceDao;

    private static final long ONE_SECOND = 100;
    private final XLogger logger = XLoggerFactory.getXLogger(PerformanceLogging.class
            .getName());

    /**
     * logs and stores a record in the database if a method takes longer than 1 second to complete it's process
     *
     * @param methodName
     * @param watch
     */
    public void isMethodProcessingBelowThreshold(String className, String methodName, StopWatch watch) {
        logger.entry(className, methodName);

        long elapsedTime = watch.getElapsedTime();

        boolean slowQuery = false;

        // if method processing elapsedTime is 1 second or over, log warning for following up.
        if(watch.getElapsedTime() >= ONE_SECOND) {
            logger.warn(format("[ %s ] method - slow performance (%s ms) review.", methodName, elapsedTime));
            slowQuery = true;
        }

        PerformanceLog performanceLog = new PerformanceLog();
        performanceLog.setClassName(className);
        performanceLog.setMethodName(methodName);
        performanceLog.setElapsedTime(elapsedTime);
        performanceLog.setSlowQuery(slowQuery);

        appPerformanceDao.insertPerformanceLog(performanceLog);

        logger.info(format("] %s ] -  method performance [ %s ]", methodName, watch.stop()));
        logger.exit();
    }

}

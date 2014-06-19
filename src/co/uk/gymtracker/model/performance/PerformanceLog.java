package co.uk.gymtracker.model.performance;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Performance Log Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/06/2014
 * @project GymTrackerApp
 */
@Document
public class PerformanceLog implements Serializable {

    private String className;
    private String methodName;
    private long elapsedTime;
    private boolean slowQuery;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public boolean isSlowQuery() {
        return slowQuery;
    }

    public void setSlowQuery(boolean slowQuery) {
        this.slowQuery = slowQuery;
    }
}

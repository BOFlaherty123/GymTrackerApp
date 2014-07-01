package co.uk.gymtracker.model.audit;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Audit Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
@Document
public class Audit {

    private String id;
    private String date;
    private String time;
    private String className;
    private String methodName;
    private String userId;
    private String username;
    private String timeElapsed;
    private String arguments;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", timeElapsed='" + timeElapsed + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }
}

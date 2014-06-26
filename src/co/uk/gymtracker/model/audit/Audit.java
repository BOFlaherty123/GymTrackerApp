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
    private String className;
    private String methodName;
    private String userId;
    private String username;
    private String arguments;

    public Audit(String date, String className, String methodName, String userId, String username, String arguments) {
        this.date = date;
        this.className = className;
        this.methodName = methodName;
        this.userId = userId;
        this.username = username;
        this.arguments = arguments;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }
}

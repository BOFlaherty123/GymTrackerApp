package co.uk.gymtracker.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Document
public class GymUser implements Serializable {

    private String id;
    @NotEmpty(message = "FirstName is compulsory")
    private String firstName;
    @NotEmpty(message = "Username is compulsory")
    private String username;
    @NotEmpty(message = "Password is compulsory")
    private String password;
    @NotEmpty(message = "LastName is compulsory")
    private String lastName;
    private String age;
    private String email;
    @NotEmpty(message = "Role is compulsory")
    private String role;

    private List<GymLogData> userSessions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GymLogData> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(List<GymLogData> userSessions) {
        this.userSessions = userSessions;
    }

    public String toString() {
        return "firstName: " + firstName + " lastName: " + lastName + " age: " + age  + " email: " + email
                + "role: " + role + " sessions: " + userSessions.toString();
    }

}
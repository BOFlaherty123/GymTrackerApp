package co.uk.gymtracker.model;

import co.uk.gymtracker.model.dashboard.ActivityAverage;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.List;

/**
 * GymUser model object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Document
public class GymUser implements Serializable {

    @AssertTrue(message="Your passwords do not match, please try again.")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

    private String id;
    @NotEmpty(message = "FirstName is compulsory")
    private String firstName;
    @NotEmpty(message = "Username is compulsory")
    private String username;
    @NotEmpty(message = "Password is compulsory")
    private String password;
    @NotEmpty(message = "ConfirmPassword is compulsory")
    private String confirmPassword;
    @NotEmpty(message = "LastName is compulsory")
    private String lastName;
    private String age;
    private String email;
    @NotEmpty(message = "Role is compulsory")
    private String role;

    private List<ActivityAverage> activityAverages;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public List<ActivityAverage> getActivityAverages() {
        return activityAverages;
    }

    public void setActivityAverages(List<ActivityAverage> activityAverages) {
        this.activityAverages = activityAverages;
    }

    @Override
    public String toString() {
        return "GymUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", activityAverages=" + activityAverages +
                '}';
    }
}
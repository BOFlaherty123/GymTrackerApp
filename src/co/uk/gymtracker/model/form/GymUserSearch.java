package co.uk.gymtracker.model.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Model object for User Search
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/06/2014
 * @project GymTrackerApp
 */
public class GymUserSearch {

    @NotEmpty
    private String username;
    private String firstName;
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}

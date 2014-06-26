package co.uk.gymtracker.model.form;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
public class AuditSearch {

    private String username;
    private String toDate;
    private String fromDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "AuditSearch{" +
                "username='" + username + '\'' +
                ", toDate='" + toDate + '\'' +
                ", fromDate='" + fromDate + '\'' +
                '}';
    }
}

package co.uk.gymtracker.model.stats;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/06/14
 * @project GymTrackerApp
 */
public class Collection {

    private String name;
    private String size;
    private String numberOfDocuments;
    private String avgObjectSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumberOfDocuments() {
        return numberOfDocuments;
    }

    public void setNumberOfDocuments(String numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }

    public String getAvgObjectSize() {
        return avgObjectSize;
    }

    public void setAvgObjectSize(String avgObjectSize) {
        this.avgObjectSize = avgObjectSize;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", numberOfDocuments='" + numberOfDocuments + '\'' +
                ", avgObjectSize='" + avgObjectSize + '\'' +
                '}';
    }
}

package co.uk.gymtracker.model.stats;

import java.util.List;

/**
 * Database Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
public class Database {

    private String name;
    private String server;
    private String size;
    private String numOfCollections;
    private List<Collection> collections;
    private String numbOfObjects;
    private String avgObjSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumOfCollections() {
        return numOfCollections;
    }

    public void setNumOfCollections(String numOfCollections) {
        this.numOfCollections = numOfCollections;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public String getNumbOfObjects() {
        return numbOfObjects;
    }

    public void setNumbOfObjects(String numbOfObjects) {
        this.numbOfObjects = numbOfObjects;
    }

    public String getAvgObjSize() {
        return avgObjSize;
    }

    public void setAvgObjSize(String avgObjSize) {
        this.avgObjSize = avgObjSize;
    }

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", size='" + size + '\'' +
                ", numOfCollections='" + numOfCollections + '\'' +
                ", collections=" + collections +
                ", numbOfObjects='" + numbOfObjects + '\'' +
                ", avgObjSize='" + avgObjSize + '\'' +
                '}';
    }
}

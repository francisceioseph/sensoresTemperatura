package sample.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by francisco on 28/03/15.
 */
public class SensorsInformation {
    private final SimpleStringProperty sensorTime;
    private final SimpleStringProperty sensorID;
    private final SimpleStringProperty sensorType;
    private final SimpleStringProperty sensorValue;
    private final SimpleStringProperty sensorMessage;

    public SensorsInformation(String sensorID,
                              String sensorTime,
                              String sensorType,
                              String sensorValue,
                              String sensorMessage) {
        this.sensorID = new SimpleStringProperty(sensorID);
        this.sensorTime = new SimpleStringProperty(sensorTime);
        this.sensorType = new SimpleStringProperty(sensorType);;
        this.sensorValue = new SimpleStringProperty(sensorValue);;
        this.sensorMessage = new SimpleStringProperty(sensorMessage);
    }

    public String getSensorID() {
        return sensorID.get();
    }

    public SimpleStringProperty sensorIDProperty() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID.set(sensorID);
    }

    public String getSensorTime() {
        return sensorTime.get();
    }

    public SimpleStringProperty sensorTimeProperty() {
        return sensorTime;
    }

    public void setSensorTime(String sensorTime) {
        this.sensorTime.set(sensorTime);
    }

    public String getSensorType() {
        return sensorType.get();
    }

    public SimpleStringProperty sensorTypeProperty() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType.set(sensorType);
    }

    public String getSensorValue() {
        return sensorValue.get();
    }

    public SimpleStringProperty sensorValueProperty() {
        return sensorValue;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue.set(sensorValue);
    }

    public String getSensorMessage() {
        return sensorMessage.get();
    }

    public SimpleStringProperty sensorMessageProperty() {
        return sensorMessage;
    }

    public void setSensorMessage(String sensorMessage) {
        this.sensorMessage.set(sensorMessage);
    }
}

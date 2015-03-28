package sample;

import javafx.collections.ObservableList;
import sample.model.SensorsInformation;

/**
 * Created by francisco on 28/03/15.
 */
public enum Singleton {
    INSTANCE;

    public ObservableList<SensorsInformation> tableData = null;
    public int sensorMeasurementsLimit;
    public String sensorID = null;
    public SensorType sensorType = null;

}

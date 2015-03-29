package sample.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import sample.model.Singleton;
import sample.clients.DaemonGUIClient;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by francisco on 28/03/15.
 */
public class SensorController implements Initializable{
    public Label sensorValue;
    public Label sensorType;
    public TextField sensorUUID;
    public ProgressBar progressIndicator;

    public void startMeasurements(final int measurementLimit){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                random.setSeed(System.currentTimeMillis());

                while(true) {
                    float measurement = random.nextInt(measurementLimit) + (random.nextInt(10)) / 10.0f;
                    Singleton.INSTANCE.sendNotificationToGUI(measurement);
                    updateSensorTextValue(measurement);

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private synchronized void updateSensorTextValue(final float value){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double progress = value/Singleton.INSTANCE.sensorMeasurementsLimit;

                Singleton.INSTANCE.labelValue.setValue(String.format("%-3.01f", value));
                progressIndicator.setProgress(progress);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton.INSTANCE.labelValue = new SimpleStringProperty("00,0");

        this.sensorUUID.setText(Singleton.INSTANCE.sensorID);
        this.sensorType.setText(Singleton.INSTANCE.sensorType.toString());
        this.sensorValue.textProperty().bind(Singleton.INSTANCE.labelValue);
        this.startMeasurements(Singleton.INSTANCE.sensorMeasurementsLimit);
    }
}

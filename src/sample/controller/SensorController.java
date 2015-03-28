package sample.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import sample.Singleton;
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
                    sendNotificationToGUI(measurement);
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

    private synchronized void sendNotificationToGUI(final float value){
        String args[] = {};
        daemonGUI.DaemonGUI gui = DaemonGUIClient.getInstance(args);
        String message = null;

        String timeStamp = String.format("%s %s",
                new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()),
                new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
        );

        switch (Singleton.INSTANCE.sensorType) {
            case COLOR:
                if (value > 20){
                    message = String.format("%s \t %11s \t %3.1f \t Poluição por material orgânico", timeStamp, Singleton.INSTANCE.sensorType, value);
                }
                else {
                    message = String.format("%s \t %11s \t %3.1f \t Cor Normal", timeStamp, Singleton.INSTANCE.sensorType, value);
                }
                break;
            case TEMPERATURE:
                if (value > 35){
                    message = String.format("%s \t %11s \t %3.1f \t Temperatura Alta", timeStamp, Singleton.INSTANCE.sensorType, value);
                }
                else{
                    if (value >= 32 && value <=35) {
                        message = String.format("%s \t %11s \t %3.1f \t Temperatura Normal", timeStamp, Singleton.INSTANCE.sensorType, value);
                    }
                    else {
                        message = String.format("%s \t %11s \t %3.1f \t Temperatura Baixa", timeStamp, Singleton.INSTANCE.sensorType, value);
                    }
                }
                break;
            case PH:
                if (value > 7){
                    message = String.format("%s \t %11s \t %3.1f \t Básica", timeStamp, Singleton.INSTANCE.sensorType, value);
                }
                else{
                    if (value == 7){
                        message = String.format("%s \t %11s \t %3.1f \t Neutra", timeStamp, Singleton.INSTANCE.sensorType, value);
                    }
                    else {
                        message = String.format("%s \t %11s \t %3.1f \t Ácida", timeStamp, Singleton.INSTANCE.sensorType, value);
                    }
                }
                break;
        }


        gui.notificarGui(Singleton.INSTANCE.sensorID, message);
    }

    private synchronized void updateSensorTextValue(final float value){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double progress = value/Singleton.INSTANCE.sensorMeasurementsLimit;

                sensorValue.setText(String.format("%-3.01f", value));
                progressIndicator.setProgress(progress);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.sensorUUID.setText(Singleton.INSTANCE.sensorID);
        this.sensorType.setText(Singleton.INSTANCE.sensorType.toString());

        System.out.println(Singleton.INSTANCE.sensorID.length());

        this.startMeasurements(Singleton.INSTANCE.sensorMeasurementsLimit);
    }
}

package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import sample.clients.DaemonGUIClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by francisco on 28/03/15.
 */
public enum Singleton {
    INSTANCE;

    public ObservableList<SensorsInformation> tableData = null;
    public int sensorMeasurementsLimit;
    public String sensorID = null;
    public SensorType sensorType = null;
    public SimpleStringProperty labelValue = null;
    public String[] args = null;

    public synchronized void sendNotificationToGUI(final float value){
        daemonGUI.DaemonGUI gui = DaemonGUIClient.getInstance(this.args);
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


        try {
            gui.notificarGui(Singleton.INSTANCE.sensorID, message);
        }
        catch (Exception e){
            System.out.println("Erro de comunicação... Verifique sua conexão com a rede ou se foi inserido um identificador válido");
        }
    }

}

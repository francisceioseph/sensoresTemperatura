package sample.idl.sensorTemperatura;

import javafx.application.Platform;
import sample.model.Singleton;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorTemperaturaImplementation extends sensorTemperatura.SensorTemperaturaPOA {
    public void alterarTemperatura(float temperatura){
        final float value = temperatura;
        final String formattedValue;

        try {
            formattedValue = String.format("%3.1f", temperatura);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Singleton.INSTANCE.labelValue.setValue(formattedValue);
                    Singleton.INSTANCE.sendNotificationToGUI(value);
                }
            });
        }
        catch (Exception e){
            System.out.println("Something wrong happened...");
            return;
        }
    }

}

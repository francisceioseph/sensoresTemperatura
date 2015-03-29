package sample.idl.sensorCor;

import javafx.application.Platform;
import sample.model.Singleton;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorCorImplementation extends sensorCor.SensorCorPOA{
    public void alterarCor(float cor){
        final float value = cor;
        final String formattedValue;

        try {
            formattedValue = String.format("%3.1f", cor);

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

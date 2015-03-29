package sample.idl.sensorPH;

import javafx.application.Platform;
import sample.model.Singleton;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorPHImplementation extends sensorPH.SensorPHPOA{
    public void alterarPH(float ph){
        final float value = ph;
        final String formattedValue;

        try {
            formattedValue = String.format("%3.1f", ph);

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

package sample.idl.daemonGUI;

import javafx.application.Platform;
import sample.Singleton;
import sample.model.SensorsInformation;

/**
 * Created by francisco on 27/03/15.
 */
public class DaemonGUIImplementation extends daemonGUI.DaemonGUIPOA{
    public void notificarGui(final String notificacaoID, String notificacao){

        final String sensorData[] = notificacao.split("\t");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                SensorsInformation newSensorInformation = new SensorsInformation(
                        notificacaoID,
                        sensorData[0],
                        sensorData[1],
                        sensorData[2],
                        sensorData[3]
                );

                Singleton.INSTANCE.tableData.add(newSensorInformation);
            }
        });
    }
}

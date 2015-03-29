package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.model.SensorType;
import sample.clients.SensorCorClient;
import sample.clients.SensorPHClient;
import sample.clients.SensorTemperaturaClient;
import sample.model.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by francisco on 28/03/15.
 */
public class SendValueController implements Initializable{
    public TextField sensorIDTextField;
    public TextField sensorValueToSend;
    public ChoiceBox sensorTypeChoiceBox;

    public void send(ActionEvent actionEvent) {
        SensorType selectedSensorType = (SensorType) sensorTypeChoiceBox.getSelectionModel().getSelectedItem();
        float parsedValueToSend;

        try{
            parsedValueToSend = Float.parseFloat(sensorValueToSend.getText());
        }
        catch (Exception e){
            System.out.println("Você não inseriu um valor válido...");
            return;
        }

        try {
            switch (selectedSensorType) {
                case COLOR:
                    sensorCor.SensorCor sensorCorObject = SensorCorClient.getInstance(sensorIDTextField.getText(), Singleton.INSTANCE.args);
                    sensorCorObject.alterarCor(parsedValueToSend);

                    break;
                case TEMPERATURE:
                    sensorTemperatura.SensorTemperatura sensorTemperaturaObject = SensorTemperaturaClient.getInstance(sensorIDTextField.getText(), Singleton.INSTANCE.args);
                    sensorTemperaturaObject.alterarTemperatura(parsedValueToSend);

                    break;
                case PH:
                    sensorPH.SensorPH sensorPHObject = SensorPHClient.getInstance(sensorIDTextField.getText(), Singleton.INSTANCE.args);
                    sensorPHObject.alterarPH(parsedValueToSend);

                    break;
                default:
                    return;
            }
        }
        catch (Exception e){
            System.out.println("Erro de comunicação... Verifique sua conexão com a rede ou se foi inserido um identificador válido");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<SensorType> choiceBoxItems = FXCollections.observableArrayList();
        choiceBoxItems.addAll(SensorType.TEMPERATURE, SensorType.PH, SensorType.COLOR);

        this.sensorTypeChoiceBox.setItems(choiceBoxItems);
    }
}

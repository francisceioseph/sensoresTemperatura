package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by francisco on 28/03/15.
 */
public class SendValueController implements Initializable{
    public TextField sensorIDTextField;
    public TextField sensorValueToSend;

    public void send(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

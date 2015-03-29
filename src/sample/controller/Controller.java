package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.Singleton;
import sample.model.SensorsInformation;
import sample.servers.DaemonGUIServer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public TableColumn idColumn;
    public TableColumn typeColumn;
    public TableColumn valueColumn;
    public TableColumn messageColumn;
    public TableView<SensorsInformation> sensorsTableView;
    public TableColumn timeColumn;

    public void closeWindow(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
        Scene sourceScene = this.sensorsTableView.getScene();
        this.loadWindow("../view/about.fxml", "About" ,sourceScene.getWindow());
    }

    public void sendValue(ActionEvent actionEvent) {
        Scene sourceScene = this.sensorsTableView.getScene();
        this.loadWindow("../view/sendValue.fxml", "Send Value to Sensor", sourceScene.getWindow());
    }

    private void loadWindow(String fxmlPath, String windowTitle, Window currentWindow) {
        FXMLLoader loader = new FXMLLoader(DaemonGUIServer.class.getResource(fxmlPath));
        Parent root = null;

        try {
            root = (Parent) loader.load();
            SendValueController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));
            stage.initOwner(currentWindow);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton.INSTANCE.tableData = FXCollections.observableArrayList();

        this.timeColumn.setCellValueFactory(
                new PropertyValueFactory<SensorsInformation, String>("sensorTime")
        );

        this.idColumn.setCellValueFactory(
                new PropertyValueFactory<SensorsInformation, String>("sensorID")

        );

        this.typeColumn.setCellValueFactory(
                new PropertyValueFactory<SensorsInformation, String>("sensorType")
        );

        this.valueColumn.setCellValueFactory(
                new PropertyValueFactory<SensorsInformation, String>("sensorValue")
        );

        this.messageColumn.setCellValueFactory(
                new PropertyValueFactory<SensorsInformation, String>("sensorMessage")
        );

        this.sensorsTableView.setItems(Singleton.INSTANCE.tableData);
    }
}

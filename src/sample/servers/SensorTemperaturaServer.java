package sample.servers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import sample.SensorType;
import sample.Singleton;
import sample.idl.sensorTemperatura.SensorTemperaturaImplementation;

import java.util.UUID;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorTemperaturaServer extends Application{
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sensor.fxml"));
        primaryStage.setTitle("Sensor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String args[]){
        String javaFXOptions[] = {};

        Singleton.INSTANCE.sensorType = SensorType.TEMPERATURE;
        Singleton.INSTANCE.sensorID = UUID.randomUUID().toString();
        Singleton.INSTANCE.sensorMeasurementsLimit = 100;

        try{
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object rootPOAReference = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(rootPOAReference);

            Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            SensorTemperaturaImplementation sensorTemperaturaImplementation = new SensorTemperaturaImplementation();
            Object sensorTemperaturaReference = rootPOA.servant_to_reference(sensorTemperaturaImplementation);

            NameComponent sensorTemperaturaName[] = {
                    new NameComponent(Singleton.INSTANCE.sensorID, "sensor")
            };

            nameService.rebind(sensorTemperaturaName, sensorTemperaturaReference);

            rootPOA.the_POAManager().activate();

            launch(javaFXOptions);
            orb.run();

        }
        catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
        catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        }
        catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }
        catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        }
        catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        }
        catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        }
    }
}
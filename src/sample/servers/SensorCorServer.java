package sample.servers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
import sample.idl.sensorCor.SensorCorImplementation;

import java.util.UUID;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorCorServer extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sensor.fxml"));
        primaryStage.setTitle("Color Sensor");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("../icons/color.png")
                )
        );
        primaryStage.show();
    }

    public static void main(String args[]){
        String javaFXOptions[] = {};

        Singleton.INSTANCE.sensorType = SensorType.COLOR;
        Singleton.INSTANCE.sensorID = UUID.randomUUID().toString();
        Singleton.INSTANCE.sensorMeasurementsLimit = 80;

        try{

            ORB orb = ORB.init(args, null);

            Object rootPOAReference = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(rootPOAReference);

            Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            SensorCorImplementation sensorCorImplementation = new SensorCorImplementation();
            Object sensorCorReference = rootPOA.servant_to_reference(sensorCorImplementation);

            NameComponent sensorCorName[] = {
                    new NameComponent(Singleton.INSTANCE.sensorID, "sensor")
            };

            nameService.rebind(sensorCorName, sensorCorReference);

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

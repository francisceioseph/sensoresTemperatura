package sample.servers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
import sample.idl.daemonGUI.DaemonGUIImplementation;
import sample.model.Singleton;

/**
 * Created by francisco on 27/03/15.
 */
public class DaemonGUIServer extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));

        primaryStage.setTitle("Sensors Monitor");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("../icons/monitor.png")
                )
        );

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.show();
    }

    public static void main(String args[]){
        Singleton.INSTANCE.args = args;
        String javaFXOptions[] = {};

        try{

            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object rootPOAReference = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(rootPOAReference);

            Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            DaemonGUIImplementation daemonGUIImplementation = new DaemonGUIImplementation();
            Object daemonGUIReference = rootPOA.servant_to_reference(daemonGUIImplementation);

            NameComponent daemonGUIName[] = {
                    new NameComponent("daemon-gui", "gui")
            };

            nameService.rebind(daemonGUIName, daemonGUIReference);

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

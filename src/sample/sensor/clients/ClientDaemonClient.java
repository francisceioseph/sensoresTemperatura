package sample.sensor.clients;

/**
 * Created by francisco on 27/03/15.
 */

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.*;
import sample.sensor.clientDaemon.ClientDaemonImplementation;

public class ClientDaemonClient {

    public clientDaemon.ClientDaemon clientDaemonImplementation = null;

    public void initClientDaemonClient(String args[]){
        try{
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            NameComponent serviceName[] = {
                    new NameComponent("Daemon", "Sensores")
            };

            org.omg.CORBA.Object clientDaemonServiceReference = nameService.resolve(serviceName);
            this.clientDaemonImplementation = clientDaemon.ClientDaemonHelper.narrow(clientDaemonServiceReference);

            this.clientDaemonImplementation.notificarGui("Shanya", "Alaia");
        }
        catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
        catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }
        catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        }
        catch (NotFound notFound) {
            notFound.printStackTrace();
        }

    }
}

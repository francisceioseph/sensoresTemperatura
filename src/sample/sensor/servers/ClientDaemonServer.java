package sample.sensor.servers;

/**
 * Created by francisco on 27/03/15.
 */

import clientDaemon.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import sample.sensor.clientDaemon.ClientDaemonImplementation;

public class ClientDaemonServer {
    public void initClientDaemonServer(String args[]){

        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object rootPOAReference = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(rootPOAReference);

            org.omg.CORBA.Object nameServerReference = orb.resolve_initial_references("NameService");
            NamingContext nameServer = NamingContextHelper.narrow(nameServerReference);

            ClientDaemonImplementation clientDaemon = new ClientDaemonImplementation();
            org.omg.CORBA.Object clientDaemonReference = rootPOA.servant_to_reference(clientDaemon);

            NameComponent clientDaemonServerName [] = {
                    new NameComponent("Daemon", "Sensores")
            };

            nameServer.rebind(clientDaemonServerName, clientDaemonReference);

            rootPOA.the_POAManager().activate();

            orb.run();

        }
        catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }

        catch (ServantNotActive servantNotActive){
            servantNotActive.printStackTrace();
        }

        catch (WrongPolicy wrongPolicy){
            wrongPolicy.printStackTrace();
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

        catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        }


    }
}

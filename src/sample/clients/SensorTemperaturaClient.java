package sample.clients;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorTemperaturaClient {

    public static sensorTemperatura.SensorTemperatura getInstance(String sensorID, String args[]){
        try{
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            NameComponent sensorTemperaturaName[] = {
                    new NameComponent(sensorID, "sensor")
            };


            Object sensorTemperaturaReference = nameService.resolve(sensorTemperaturaName);
            sensorTemperatura.SensorTemperatura sensorTemperaturaObject = sensorTemperatura.SensorTemperaturaHelper.narrow(sensorTemperaturaReference);

            return sensorTemperaturaObject;
        }
        catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
        catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        }
        catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }

        return null;
    }
}

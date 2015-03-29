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
public class SensorCorClient {

    public static sensorCor.SensorCor getInstance(String sensorID, String args[]){
        try{
            ORB orb = ORB.init(args, null);

            Object nameServiceReference = orb.resolve_initial_references("NameService");
            NamingContext nameService = NamingContextHelper.narrow(nameServiceReference);

            NameComponent sensorCorName[] = {
                    new NameComponent(sensorID, "sensor")
            };

            Object sensorCorReference = nameService.resolve(sensorCorName);
            sensorCor.SensorCor sensorCorObject = sensorCor.SensorCorHelper.narrow(sensorCorReference);

            return sensorCorObject;
        }
        catch (InvalidName invalidName) {
            System.out.println("Nome de Serviço Inválido.");
        }
        catch (CannotProceed cannotProceed) {
            System.out.println("Não foi possível prosseguir com a solicitação");
        }
        catch (NotFound notFound) {
            System.out.println("Objeto não encontrado");
        }
        catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            System.out.println("Nome de Serviço Inválido.");
        }


        return null;
    }
}

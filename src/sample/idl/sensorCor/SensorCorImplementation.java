package sample.idl.sensorCor;

/**
 * Created by francisco on 27/03/15.
 */
public class SensorCorImplementation extends sensorCor.SensorCorPOA{
    float corCode;

    public void alterarCor(float cor){
        this.corCode = cor;
        System.out.println("" + this.corCode);
    }
}

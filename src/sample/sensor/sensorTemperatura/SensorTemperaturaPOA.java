package sensorTemperatura;


/**
* sensorTemperatura/SensorTemperaturaPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorTemperatura.idl
* Sexta-feira, 27 de Março de 2015 13h53min45s BRT
*/

public abstract class SensorTemperaturaPOA extends org.omg.PortableServer.Servant
 implements sensorTemperatura.SensorTemperaturaOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("alterarTemperatura", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // sensorTemperatura/SensorTemperatura/alterarTemperatura
       {
         float temperatura = in.read_float ();
         this.alterarTemperatura (temperatura);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sensorTemperatura/SensorTemperatura:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SensorTemperatura _this() 
  {
    return SensorTemperaturaHelper.narrow(
    super._this_object());
  }

  public SensorTemperatura _this(org.omg.CORBA.ORB orb) 
  {
    return SensorTemperaturaHelper.narrow(
    super._this_object(orb));
  }


} // class SensorTemperaturaPOA
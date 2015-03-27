package sensorPH;


/**
* sensorPH/SensorPHPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorPH.idl
* Sexta-feira, 27 de Março de 2015 13h53min40s BRT
*/

public abstract class SensorPHPOA extends org.omg.PortableServer.Servant
 implements sensorPH.SensorPHOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("alterarPH", new java.lang.Integer (0));
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
       case 0:  // sensorPH/SensorPH/alterarPH
       {
         float ph = in.read_float ();
         this.alterarPH (ph);
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
    "IDL:sensorPH/SensorPH:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SensorPH _this() 
  {
    return SensorPHHelper.narrow(
    super._this_object());
  }

  public SensorPH _this(org.omg.CORBA.ORB orb) 
  {
    return SensorPHHelper.narrow(
    super._this_object(orb));
  }


} // class SensorPHPOA
package sensorCor;


/**
* sensorCor/_SensorCorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorCor.idl
* Sexta-feira, 27 de Março de 2015 13h53min33s BRT
*/

public class _SensorCorStub extends org.omg.CORBA.portable.ObjectImpl implements sensorCor.SensorCor
{

  public void alterarCor (float cor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("alterarCor", true);
                $out.write_float (cor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                alterarCor (cor        );
            } finally {
                _releaseReply ($in);
            }
  } // alterarCor

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sensorCor/SensorCor:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _SensorCorStub

package sensorCor;

/**
* sensorCor/SensorCorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorCor.idl
* Sexta-feira, 27 de Março de 2015 13h53min33s BRT
*/

public final class SensorCorHolder implements org.omg.CORBA.portable.Streamable
{
  public sensorCor.SensorCor value = null;

  public SensorCorHolder ()
  {
  }

  public SensorCorHolder (sensorCor.SensorCor initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sensorCor.SensorCorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sensorCor.SensorCorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sensorCor.SensorCorHelper.type ();
  }

}

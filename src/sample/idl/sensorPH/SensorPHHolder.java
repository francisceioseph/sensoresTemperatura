package sensorPH;

/**
* sensorPH/SensorPHHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorPH.idl
* Sexta-feira, 27 de Março de 2015 22h54min24s BRT
*/

public final class SensorPHHolder implements org.omg.CORBA.portable.Streamable
{
  public sensorPH.SensorPH value = null;

  public SensorPHHolder ()
  {
  }

  public SensorPHHolder (sensorPH.SensorPH initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sensorPH.SensorPHHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sensorPH.SensorPHHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sensorPH.SensorPHHelper.type ();
  }

}
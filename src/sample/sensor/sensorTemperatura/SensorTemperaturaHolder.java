package sensorTemperatura;

/**
* sensorTemperatura/SensorTemperaturaHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorTemperatura.idl
* Sexta-feira, 27 de Março de 2015 13h53min45s BRT
*/

public final class SensorTemperaturaHolder implements org.omg.CORBA.portable.Streamable
{
  public sensorTemperatura.SensorTemperatura value = null;

  public SensorTemperaturaHolder ()
  {
  }

  public SensorTemperaturaHolder (sensorTemperatura.SensorTemperatura initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sensorTemperatura.SensorTemperaturaHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sensorTemperatura.SensorTemperaturaHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sensorTemperatura.SensorTemperaturaHelper.type ();
  }

}

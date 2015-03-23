package sample.model;

/**
 * Created by francisco on 23/03/15.
 */
public enum Singleton {
    INSTANCE;

    float sensorCorValue;
    float sensorPHValue;
    float sensorTemperaturaValue;

    String notificacaoCor;
    String notificacaoPH;
    String notificacaoTemperatura;

    public float getSensorCorValue() {
        return sensorCorValue;
    }

    public void setSensorCorValue(float sensorCorValue) {
        this.sensorCorValue = sensorCorValue;
    }

    public float getSensorPHValue() {
        return sensorPHValue;
    }

    public void setSensorPHValue(float sensorPHValue) {
        this.sensorPHValue = sensorPHValue;
    }

    public float getSensorTemperaturaValue() {
        return sensorTemperaturaValue;
    }

    public void setSensorTemperaturaValue(float sensorTemperaturaValue) {
        this.sensorTemperaturaValue = sensorTemperaturaValue;
    }

    public String getNotificacaoCor() {
        return notificacaoCor;
    }

    public void setNotificacaoCor(String notificacaoCor) {
        this.notificacaoCor = notificacaoCor;
    }

    public String getNotificacaoPH() {
        return notificacaoPH;
    }

    public void setNotificacaoPH(String notificacaoPH) {
        this.notificacaoPH = notificacaoPH;
    }

    public String getNotificacaoTemperatura() {
        return notificacaoTemperatura;
    }

    public void setNotificacaoTemperatura(String notificacaoTemperatura) {
        this.notificacaoTemperatura = notificacaoTemperatura;
    }
}

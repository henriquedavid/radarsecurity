package br.imd.smartmetropoles.radarseguro.model;

public class SensorCamera {

    private String id;
    private String type;
    private SensorTypeCamera Tipo;
    private SensorCoordenadasCamera coordenadas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SensorTypeCamera getTipo() {
        return Tipo;
    }

    public void setTipo(SensorTypeCamera tipo) {
        Tipo = tipo;
    }

    public SensorCoordenadasCamera getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(SensorCoordenadasCamera coordenadas) {
        this.coordenadas = coordenadas;
    }
}

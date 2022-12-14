package br.imd.smartmetropoles.radarseguro.model;

public class SensorEfetivo {

    private String id;

    private String type;

    private Data status;

    private Data coordenadas;

    private Data nomeEfetivo;

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

    public Data getStatus() {
        return status;
    }

    public void setStatus(Data status) {
        this.status = status;
    }

    public Data getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Data coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Data getNomeEfetivo() {
        return nomeEfetivo;
    }

    public void setNomeEfetivo(Data nomeEfetivo) {
        this.nomeEfetivo = nomeEfetivo;
    }
}

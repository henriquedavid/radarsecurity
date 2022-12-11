package br.imd.smartmetropoles.radarseguro.model;

public class OcorrenciaElement {

    private String id;
    private String type;
    private Data coordenadas;
    private Data grauurgencia;
    private Data idsensor;
    private Data status;
    private Data tipo;

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

    public Data getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Data coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Data getGrauurgencia() {
        return grauurgencia;
    }

    public void setGrauurgencia(Data grauurgencia) {
        this.grauurgencia = grauurgencia;
    }

    public Data getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(Data idsensor) {
        this.idsensor = idsensor;
    }

    public Data getStatus() {
        return status;
    }

    public void setStatus(Data status) {
        this.status = status;
    }

    public Data getTipo() {
        return tipo;
    }

    public void setTipo(Data tipo) {
        this.tipo = tipo;
    }
}

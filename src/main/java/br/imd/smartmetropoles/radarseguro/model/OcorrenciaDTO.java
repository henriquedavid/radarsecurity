package br.imd.smartmetropoles.radarseguro.model;

public class OcorrenciaDTO {

    private String id;
    private String status;
    private String tipo;
    private String grauurgencia;
    private String idsensor;
    private String coordenadas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGrauurgencia() {
        return grauurgencia;
    }

    public void setGrauurgencia(String grauurgencia) {
        this.grauurgencia = grauurgencia;
    }

    public String getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(String idsensor) {
        this.idsensor = idsensor;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}

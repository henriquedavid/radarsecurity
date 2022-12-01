package br.imd.smartmetropoles.radarseguro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ContextElements {
    private List<ContextEntity> contextElements;

    public List<ContextEntity> getContextElements() {
        return contextElements;
    }

    public void setContextElements(List<ContextEntity> contextElements) {
        this.contextElements = contextElements;
    }
}

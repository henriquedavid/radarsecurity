package br.imd.smartmetropoles.radarseguro.controller;

import br.imd.smartmetropoles.radarseguro.model.ContextElements;
import br.imd.smartmetropoles.radarseguro.model.OcorrenciaElement;
import br.imd.smartmetropoles.radarseguro.model.SensorCamera;
import br.imd.smartmetropoles.radarseguro.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping
    public List<SensorCamera> getCamerasSensor() throws IOException {
        return informationService.getSensorCamera();
    }

    @GetMapping("/ocorrencias")
    public List<OcorrenciaElement> getOcorrencias() throws IOException{
        return informationService.getOcorrencia();
    }

}

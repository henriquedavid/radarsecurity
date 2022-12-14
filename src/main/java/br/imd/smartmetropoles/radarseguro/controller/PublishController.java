package br.imd.smartmetropoles.radarseguro.controller;

import br.imd.smartmetropoles.radarseguro.model.ContextEntity;
import br.imd.smartmetropoles.radarseguro.model.EfetivoDTO;
import br.imd.smartmetropoles.radarseguro.model.EfetivoUpdateDTO;
import br.imd.smartmetropoles.radarseguro.model.OcorrenciaDTO;
import br.imd.smartmetropoles.radarseguro.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private PublishService publishService;

    @PostMapping
    public ResponseEntity<?> publish(@RequestBody ContextEntity contextEntity) throws IOException {
        return publishService.createContext(contextEntity);
    }

    @PostMapping("/efetivo/new")
    public ResponseEntity<?> createEfetivo(@RequestBody EfetivoDTO efetivoDTO) throws IOException {
        return publishService.createContextEquipe(efetivoDTO);
    }

    @PostMapping("/efetivo/update/coordenadas")
    public ResponseEntity<?> updateEfetivo(@RequestBody EfetivoUpdateDTO efetivoUpdateDTO) throws IOException {
        return publishService.updateContextEfetivoCoordenadas(efetivoUpdateDTO.getId(), efetivoUpdateDTO.getPosicao());
    }

    @GetMapping("/efetivo/update/status")
    public ResponseEntity<?> changeStatusEfetivo(@RequestParam("id") String id, @RequestParam("status") String status) throws IOException {
        return publishService.updateStatusEfetivo(id, status);
    }

    @PostMapping("/ocorrencia/create")
    public ResponseEntity<?> createOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO) throws IOException {
        return publishService.createOcorrencia(ocorrenciaDTO);
    }

    @PostMapping("/ocorrencias/linkar")
    public ResponseEntity<?> enviarParaEquipe(@RequestParam("idequipe") String equipeId, @RequestParam("idocorrencia") String ocorrenciaId) throws IOException {
        return publishService.enviarParaEquipe(ocorrenciaId, equipeId);
    }

    @PostMapping("/efetivo/aleatorio")
    public ResponseEntity<?> gerarEfetivoAleatorio() throws IOException{
        return publishService.gerarEfetivoAleatorio();
    }
}

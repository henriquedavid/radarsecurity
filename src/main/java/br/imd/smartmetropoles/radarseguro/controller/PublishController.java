package br.imd.smartmetropoles.radarseguro.controller;

import br.imd.smartmetropoles.radarseguro.model.ContextEntity;
import br.imd.smartmetropoles.radarseguro.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package br.imd.smartmetropoles.radarseguro.controller;

import br.imd.smartmetropoles.radarseguro.model.ContextSubscribe;
import br.imd.smartmetropoles.radarseguro.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService service;

    @PostMapping
    public ResponseEntity<?> subscribeInCamerasSensor(@RequestBody ContextSubscribe contextSubscribe){
        return service.subscribeInCamerasSensor(contextSubscribe);
    }

    @PostMapping("/sensorcamera")
    public void subscribeSensorCamera(@RequestHeader @RequestBody @RequestAttribute Object orionNotification){
        service.sendSseEventSensorCamera(orionNotification);
    }


}

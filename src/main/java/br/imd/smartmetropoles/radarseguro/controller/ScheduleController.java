package br.imd.smartmetropoles.radarseguro.controller;

import br.imd.smartmetropoles.radarseguro.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
    public void simularOcorrencias(){
        System.out.println("Simulando ocorrência");
        scheduleService.criarOcorrenciaAleatoria();
    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void simularEfetivo() throws IOException {
        System.out.println("Simulando efetivo");
        scheduleService.moverEfetivoAleatoriamente();
    }

}

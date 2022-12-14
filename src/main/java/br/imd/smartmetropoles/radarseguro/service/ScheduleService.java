package br.imd.smartmetropoles.radarseguro.service;

import br.imd.smartmetropoles.radarseguro.model.OcorrenciaDTO;
import br.imd.smartmetropoles.radarseguro.model.SensorEfetivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ScheduleService {

    @Autowired
    PublishService publishService;

    @Autowired
    private InformationService informationService;

    @Autowired
    LocalizationService localizationService;

    private Random random;

    public ScheduleService(){
        random = new Random();
    }


    public void criarOcorrenciaAleatoria(){
        OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();

        //ocorrenciaDTO.setId(UUID.randomUUID().toString());
        ocorrenciaDTO.setCoordenadas(localizationService.getRandomCoordinate());
        ocorrenciaDTO.setGrauurgencia(gerarGrauUrgenciaAleatorio());
        ocorrenciaDTO.setStatus("Aberta");
        ocorrenciaDTO.setTipo(gerarTipoAleatorio());
        ocorrenciaDTO.setIdsensor("" + random.nextInt(1,100));

        //System.out.println("Criando ocorrencia de id " + ocorrenciaDTO.getId());

        try {
            publishService.createOcorrencia(ocorrenciaDTO);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void moverEfetivoAleatoriamente() throws IOException {
        List<SensorEfetivo> efetivos = informationService.getEnfetivo();

        for (int i = 0; i < efetivos.size(); i++){

            publishService.updateContextEfetivoCoordenadas("" + (i+1), localizationService.getRandomCoordinate());
        }

    }

    private String gerarGrauUrgenciaAleatorio(){
        int sel = random.nextInt(0,3);
        switch (sel){
            case 0:
                return "Baixa";
            case 1:
                return "Media";
            case 2:
            default:
                return "Alta";
        }
    }

    private String gerarTipoAleatorio(){
        int sel = random.nextInt(0,3);
        switch (sel){
            case 0:
                return "Acidente de transito";
            case 1:
                return "Assalto ou furto";
            case 2:
                return "Fiscalizacao";
            case 3:
            default:
                return "Outro";
        }
    }
}

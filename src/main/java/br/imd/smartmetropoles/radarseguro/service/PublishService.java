package br.imd.smartmetropoles.radarseguro.service;

import br.imd.smartmetropoles.radarseguro.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class PublishService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InformationService informationService;

    @Autowired
    private LocalizationService localizationService;

    private ObjectMapper objectMapper;

    public ResponseEntity<?> createContext(ContextEntity contextEntity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/contextEntities");
        contextEntity.setId(contextEntity.getId()+(informationService.getSensorCamera().size()+1));
        String json = new Gson().toJson(contextEntity);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> createContextEquipe(EfetivoDTO efetivoDTO) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/contextEntities");

        ContextEntity contextEntity_ = new ContextEntity();
        Data data_ = new Data();
        data_.setName("coordenadas");
        data_.setType("coords");
        data_.setValue(efetivoDTO.getCoordenadas());

        Data data_1 = new Data();
        data_1.setName("nomeEfetivo");
        data_1.setType("String");
        data_1.setValue(efetivoDTO.getNome());

        Data data_2 = new Data();
        data_2.setName("Status");
        data_2.setType("String");
        data_2.setValue(efetivoDTO.getStatus());

        Data metadata_coors = new Data();
        metadata_coors.setType("DateTime");
        metadata_coors.setName("Data");
        metadata_coors.setValue((new Date()).toString());

        List<Data> metas = Arrays.asList(metadata_coors);
        data_.setMetadatas(metas);
        data_1.setMetadatas(metas);
        data_2.setMetadatas(metas);

        List<Data> attributes_ = Arrays.asList(data_, data_1, data_2);

        contextEntity_.setPattern(false);
        contextEntity_.setId(String.valueOf(informationService.getEnfetivo().size()+1));
        contextEntity_.setType("Efetivo_Police");
        contextEntity_.setAttributes(attributes_);

        String json = new Gson().toJson(contextEntity_);
        System.out.println(json);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            System.out.println(EntityUtils.toString(response.getEntity()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateContextEfetivoCoordenadas(String id, String posicao) throws IOException {
        ContextElements contextElements = new ContextElements();
        ContextEntity contextEntity = new ContextEntity();
        contextEntity.setType("Efetivo_Police");
        contextEntity.setPattern(false);
        contextEntity.setId(id);

        Data data_ = new Data();
        data_.setName("coordenadas");
        data_.setValue(posicao);
        data_.setType("coords");

        contextEntity.setAttributes(Arrays.asList(data_));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/updateContext");

        contextElements.setContextElements(Arrays.asList(contextEntity));
        contextElements.setUpdateAction("UPDATE");

        String json = new Gson().toJson(contextElements);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateStatusEfetivo(String id, String status) throws IOException {
        ContextElements contextElements = new ContextElements();
        ContextEntity contextEntity = new ContextEntity();
        contextEntity.setType("Efetivo_Police");
        contextEntity.setPattern(false);
        contextEntity.setId(id);

        Data data_ = new Data();
        data_.setName("status");
        data_.setValue(status);
        data_.setType("String");

        contextEntity.setAttributes(Arrays.asList(data_));


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/updateContext");

        contextElements.setContextElements(Arrays.asList(contextEntity));
        contextElements.setUpdateAction("UPDATE");
        String json = new Gson().toJson(contextElements);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> createOcorrencia(OcorrenciaDTO ocorrenciaDTO) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/contextEntities");

        ContextEntity contextEntity_ = new ContextEntity();
        Data data_ = new Data();
        data_.setName("coordenadas");
        data_.setType("coords");
        data_.setValue(ocorrenciaDTO.getCoordenadas());

        Data data_1 = new Data();
        data_1.setName("status");
        data_1.setType("String");
        data_1.setValue(ocorrenciaDTO.getStatus());

        Data data_2 = new Data();
        data_2.setName("tipo");
        data_2.setType("String");
        data_2.setValue(ocorrenciaDTO.getTipo());

        Data data_3 = new Data();
        data_3.setName("grauurgencia");
        data_3.setType("String");
        data_3.setValue(ocorrenciaDTO.getGrauurgencia());

        Data data_4 = new Data();
        data_4.setName("idsensor");
        data_4.setType("String");
        data_4.setValue(ocorrenciaDTO.getIdsensor());

        //Data data_5 = new Data();
        //data_5.setName("idequipe");
        //data_5.setValue("");
        //data_5.setType("String");

        List<SensorEfetivo> l = informationService.getEnfetivo();
        Random random = new Random();
        //String lid = l.get(random.nextInt(0, l.size())).getId();
        String lid = "" + random.nextInt(1,l.size() + 1);

        Data data_5 = new Data();
        data_5.setName("idequipe");
        data_5.setType("String");
        data_5.setValue(lid);

        Data metadata_coors = new Data();
        metadata_coors.setType("DateTime");
        metadata_coors.setName("Data");
        metadata_coors.setValue((new Date()).toString());

        List<Data> metas = Arrays.asList(metadata_coors);
        data_.setMetadatas(metas);
        data_1.setMetadatas(metas);
        data_2.setMetadatas(metas);
        data_3.setMetadatas(metas);
        data_4.setMetadatas(metas);
        data_5.setMetadatas(metas);

        List<Data> attributes_ = Arrays.asList(data_, data_1, data_2, data_3, data_4, data_5);

        contextEntity_.setPattern(false);
        contextEntity_.setId(String.valueOf(informationService.getOcorrencia().size()+1));
        contextEntity_.setType("Ocorrencia");
        contextEntity_.setAttributes(attributes_);

        String json = new Gson().toJson(contextEntity_);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == 200){
            //System.out.println(EntityUtils.toString(response.getEntity()));
            try{
                return new ResponseEntity<>(HttpStatus.OK);
            } finally {
                updateStatusEfetivo(lid, "Ocupado");
            }

        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> enviarParaEquipe(String idOcorrencia, String idEquipe) throws IOException {
        ContextElements contextElements = new ContextElements();
        ContextEntity contextEntity = new ContextEntity();
        contextEntity.setType("Ocorrencia");
        contextEntity.setPattern(false);
        contextEntity.setId(idOcorrencia);

        Data data_ = new Data();
        data_.setName("idequipe");
        data_.setValue(idEquipe);
        data_.setType("String");

        contextEntity.setAttributes(Arrays.asList(data_));


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/updateContext");

        contextElements.setContextElements(Arrays.asList(contextEntity));
        contextElements.setUpdateAction("UPDATE");
        String json = new Gson().toJson(contextElements);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            System.out.println(EntityUtils.toString(response.getEntity()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> gerarEfetivoAleatorio() throws IOException {

        for (int i = 0; i < 10; i++ ){
            EfetivoDTO efetivoDTO = new EfetivoDTO();

            efetivoDTO.setCoordenadas(localizationService.getRandomCoordinate());
            efetivoDTO.setData(LocalDate.now().toString());
            efetivoDTO.setNome("Efetivo " + (i + 1));
            efetivoDTO.setStatus("Livre");

            ResponseEntity<?> response = createContextEquipe(efetivoDTO);

            if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return response;
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

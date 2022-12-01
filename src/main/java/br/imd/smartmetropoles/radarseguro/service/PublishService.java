package br.imd.smartmetropoles.radarseguro.service;

import br.imd.smartmetropoles.radarseguro.model.ContextEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;


@Service
public class PublishService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InformationService informationService;

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

//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        headers.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(contextEntity.toString().getBytes().length));
//        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//
//        objectMapper = new ObjectMapper();
//        ResponseEntity<ContextEntity> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                httpEntity,
//                ContextEntity.class,
//                objectMapper.writeValueAsString(contextEntity)
//        );
//
//        System.out.println("TESTE");
//        return response;

    }
}

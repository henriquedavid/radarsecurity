package br.imd.smartmetropoles.radarseguro.service;

import br.imd.smartmetropoles.radarseguro.model.ContextSubscribe;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeService {

    public ResponseEntity<?> subscribeInCamerasSensor(ContextSubscribe contextSubscribe){
        HttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://10.7.41.94:1026/v1/subscribeContext");
        String json = new Gson().toJson(contextSubscribe);

        StringEntity entity = null;

        try{
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        if(entity != null){
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Context-Type", "application/json");

            HttpResponse response = null;
            try{
                response = httpClient.execute(httpPost);
            } catch (IOException e){
                e.printStackTrace();
            }

            if(response.getStatusLine().getStatusCode() == 200){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void sendSseEventSensorCamera(Object orionNotification){
        List<SseEmitter> emitted = new ArrayList<>();

        JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(orionNotification))
                .getAsJsonObject().getAsJsonArray("contextResponses")
                .get(0).getAsJsonObject().getAsJsonObject("contextElement");
        String id = jsonObject.get("id").getAsString();

        orionNotification = new Gson().fromJson(jsonObject, Object.class);

//        SseEmitter sseEmitter =

    }
}

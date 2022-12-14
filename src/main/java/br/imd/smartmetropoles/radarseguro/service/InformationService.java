package br.imd.smartmetropoles.radarseguro.service;

import br.imd.smartmetropoles.radarseguro.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InformationService {

    public List<SensorCamera> getSensorCamera() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://10.7.41.94:1026/v2/entities?type=Sensor_Camera");

        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            List<SensorCamera> getContext = gson.fromJson(jsonString, List.class);
            return getContext;
        } else{
            return null;
        }
    }

    public List<SensorEfetivo> getEfetivo() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://10.7.41.94:1026/v2/entities?type=Efetivo_Police");

        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            SensorEfetivo[] getContext = new Gson().fromJson(jsonString, SensorEfetivo[].class);

//            for(SensorEfetivo e: getContext){
//                System.out.println("Obtendo sensor: " + e.getId());
//            }

            return Arrays.asList(getContext);

        } else{
            return null;
        }
    }

    public List<OcorrenciaElement> getOcorrencia() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://10.7.41.94:1026/v2/entities?type=Ocorrencia");

        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            List<OcorrenciaElement> getContext = gson.fromJson(jsonString, List.class);
            return getContext;
        } else{
            return null;
        }
    }

    public List<OcorrenciaElement> findOcorrenciaById(String id) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://10.7.41.94:1026/v2/entities?type=Ocorrencia&id="+id);

        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            List<OcorrenciaElement> getContext = gson.fromJson(jsonString, List.class);
            return getContext;
        } else{
            return null;
        }
    }
}

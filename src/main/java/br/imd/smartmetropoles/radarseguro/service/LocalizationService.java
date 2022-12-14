package br.imd.smartmetropoles.radarseguro.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LocalizationService {

    private static final double MIN_LAT = -5.870137;
    private static final double MIN_LON = -35.242961;
    private static final double MAX_LAT = -5.798168;
    private static final double MAX_LON = -35.190668;
    private static final double MAX_INCREMENT = 0.000035;

    private Random random;

    public LocalizationService(){
        random = new Random();
    }

    public String getRandomCoordinate(){
        double lat = random.nextDouble(MIN_LAT, MAX_LAT);
        double lon = random.nextDouble(MIN_LON, MAX_LON);
        return "" + lat + "," + lon;
    }

    public String moveCoordinate(String c){
        String[] coordinates = c.split(",");
        double lat0 = Double.parseDouble(coordinates[0]);
        double lon0 = Double.parseDouble(coordinates[1]);

        double lat = random.nextDouble(
                Math.max(lat0 - MAX_INCREMENT, MIN_LAT),
                Math.min(lat0 + MAX_INCREMENT, MAX_LAT)
        );

        double lon = random.nextDouble(
                Math.max(lon0 - MAX_INCREMENT, MIN_LON),
                Math.min(lon0 + MAX_INCREMENT, MAX_LON)
        );

        return "" + lat + "," + lon;
    }

}

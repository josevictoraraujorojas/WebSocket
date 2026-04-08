package org.example.websocket.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TemperaturService {
    private final RestTemplate restTemplate = new RestTemplate();

    public double buscarTemperatura(double lat, double lon) {

        String url = "https://api.open-meteo.com/v1/forecast?latitude="
                + lat + "&longitude=" + lon + "&current_weather=true";

        Map response = restTemplate.getForObject(url, Map.class);
        Map current = (Map) response.get("current_weather");

        return Double.parseDouble(current.get("temperature").toString());
    }
}

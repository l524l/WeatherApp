package com.l524l.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;

public class JSONAdapter {
    private Weather weather = new Weather();
    private String jsonAnsver;
    private JsonNode node;

    public Weather getWeather(String city, String contryCode) throws RequestExcepion, CityNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        GetRequest request = new GetRequest();
        jsonAnsver = request.makeRequest(city, contryCode);
        System.out.println(jsonAnsver);
        if (request.isRequestError()) throw new RequestExcepion();
        if (!jsonAnsver.isEmpty()){
            try {
                 node = mapper.readValue(jsonAnsver, JsonNode.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            node = node.get("data").get(0);

            weather.setTemp(node.get("temp").asDouble());
            weather.setApp_temp(node.get("app_temp").asDouble());
            weather.setSunset(node.get("sunset").asText());
            weather.setSunrise(node.get("sunrise").asText());
            weather.setPress(node.get("pres").asDouble());
            weather.setVisibility(node.get("vis").asDouble());
            weather.setWind_speed(node.get("wind_spd").asDouble());
            weather.setWind_cdir_full(node.get("wind_cdir_full").asText());
            weather.setRh(node.get("rh").asDouble());
            weather.setDescription(node.get("weather").get("description").asText());
            weather.setIcon(node.get("weather").get("icon").asText());
            weather.setSnow(node.get("snow").asDouble());
            weather.setDatetime(node.get("datetime").asText());
        }else throw new CityNotFoundException();
        return weather;
    }
}

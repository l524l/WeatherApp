package com.l524l.weather;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;

import java.io.DataOutput;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class JSONAdapter {


    public Weather getWeather(String city, String contryCode) throws RequestExcepion, CityNotFoundException {
        Weather weather = new Weather();
        String jsonAnsver;
        JsonNode node = null;


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

    public Settings getSettings(){
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.getNodeFactory().arrayNode().toString());


        Settings settings = new Settings();
        try {
            node = mapper.readValue(new File("settings.properties"), JsonNode.class);
            settings.setCities(mapper.readValue(node.get("city").toString(), new TypeReference<List<String>>(){}));
            settings.setCompact(node.get("compact").asBoolean());
            settings.setDarkMode(node.get("dark_mode").asBoolean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settings;
    }
}

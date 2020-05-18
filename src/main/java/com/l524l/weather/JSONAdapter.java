package com.l524l.weather;

import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;
import org.json.JSONObject;

public class JSONAdapter {
    private Weather weather = new Weather();
    private String jsonAnsver;
    private JSONObject jsonObject;

    public Weather getWeather(String city, String contryCode) throws RequestExcepion, CityNotFoundException {
        GetRequest request = new GetRequest();
        jsonAnsver = request.makeRequest(city, contryCode);
        if (request.isRequestError()) throw new RequestExcepion();
        if (!jsonAnsver.isEmpty()){
            System.out.println(jsonAnsver);
            jsonObject = new JSONObject(jsonAnsver);
            JSONObject object = jsonObject.getJSONArray("data").getJSONObject(0);
            weather.setTemp(object.getDouble("temp"));
            weather.setApp_temp(object.getDouble("app_temp"));
            weather.setSunset(object.getString("sunset"));
            weather.setSunrise(object.getString("sunrise"));
            weather.setPress(object.getDouble("pres"));
            weather.setVisibility(object.getDouble("vis"));
            weather.setWind_speed(object.getDouble("wind_spd"));
            weather.setWind_cdir_full(object.getString("wind_cdir_full"));
            weather.setRh(object.getDouble("rh"));
            weather.setDescription(object.getJSONObject("weather").getString("description"));
            weather.setIcon(object.getJSONObject("weather").getString("icon"));
            weather.setSnow(object.getDouble("snow"));
            weather.setDatetime(object.getString("datetime"));
        }else throw new CityNotFoundException();

        return weather;
    }
}

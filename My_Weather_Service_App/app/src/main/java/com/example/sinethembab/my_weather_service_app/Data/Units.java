package com.example.sinethembab.my_weather_service_app.Data;

import org.json.JSONObject;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public class Units implements JSONPopulator {
    public String getTemperature() {
        return temperature;
    }

    private  String temperature;
    @Override
    public void populate(JSONObject data) {

        temperature = data.optString("temperature");

    }
}

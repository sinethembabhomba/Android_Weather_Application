package com.example.sinethembab.my_weather_service_app.Data;

import org.json.JSONObject;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public class Condition implements JSONPopulator {

    private  int code;
    private int temperature;
    private String description;

    public int getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    public int getTemperature()
    {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperature=data.optInt("temp");
        description=data.optString("text");
    }
}

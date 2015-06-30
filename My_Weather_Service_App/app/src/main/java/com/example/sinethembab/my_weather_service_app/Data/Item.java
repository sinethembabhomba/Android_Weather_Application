package com.example.sinethembab.my_weather_service_app.Data;

import org.json.JSONObject;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition()
    {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {

        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}

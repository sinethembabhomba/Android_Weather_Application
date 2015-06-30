package com.example.sinethembab.my_weather_service_app.Data;

import org.json.JSONObject;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public class Channel implements JSONPopulator {

    private Item item;
    private Units units;

    public Item getItem()
    {
        return item;
    }

    public Units getUnits()
    {
        return units;
    }

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));


    }
}

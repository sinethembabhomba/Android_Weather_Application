package com.example.sinethembab.my_weather_service_app.Service;

import com.example.sinethembab.my_weather_service_app.Data.Channel;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}

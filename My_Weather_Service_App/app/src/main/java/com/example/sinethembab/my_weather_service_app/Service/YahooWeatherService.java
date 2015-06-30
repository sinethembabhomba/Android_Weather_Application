package com.example.sinethembab.my_weather_service_app.Service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.sinethembab.my_weather_service_app.Data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SinethembaB on 2015-06-29.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public String getLocation()
    {
        return location;
    }

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }



    public void refreshWeather(final String l) {

        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YOQ = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", strings[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YOQ));
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    InputStream inputstream = connection.getInputStream();
                    BufferedReader read = new BufferedReader(new InputStreamReader(inputstream));
                    StringBuilder results = new StringBuilder();
                    String line;

                    while ((line = read.readLine()) != null )
                    {
                        results.append(line);
                    }
                    return results.toString();

                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s)
            {
                if(s == null && error != null)
                {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");
                    if(count == 0)
                    {
                        callback.serviceFailure(new locationWeatherException("No weather information found for " + location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(location);

    }

    public  class locationWeatherException extends Exception{
        public locationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}

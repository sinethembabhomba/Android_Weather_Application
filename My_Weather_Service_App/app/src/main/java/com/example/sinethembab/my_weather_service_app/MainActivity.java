package com.example.sinethembab.my_weather_service_app;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinethembab.my_weather_service_app.Data.Channel;
import com.example.sinethembab.my_weather_service_app.Data.Item;
import com.example.sinethembab.my_weather_service_app.Service.WeatherServiceCallback;
import com.example.sinethembab.my_weather_service_app.Service.YahooWeatherService;


public class MainActivity extends ActionBarActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView locationTestView;
    private TextView conditionTextView;

    private YahooWeatherService service;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView=(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTestView =(TextView)findViewById(R.id.locationTestView);

        service = new YahooWeatherService(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....weather results");
        service.refreshWeather("Cape Town, ZA");
        dialog.show();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void serviceSuccess(Channel channel) {
     dialog.hide();

        Item item = channel.getItem();
        int resourcesId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        Drawable drawableIcon = getResources().getDrawable(resourcesId);
        weatherIconImageView.setImageDrawable(drawableIcon);
        temperatureTextView.setText(item.getCondition().getTemperature() +"\u00B0"+ channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTestView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();

    }
}

package weather;
//package weather;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import weather.core.WeatherService;
import weather.core.WeatherServiceEngine;

import weather.core.entity.Location;
import weather.core.entity.WeatherShot;
/*
 *Implementation of WeatherServiceEngine to integrate the app with  openweathermap.org weather provider
 * */
public class WeatherServiceOwmSecond extends WeatherServiceEngine implements WeatherService {

    private final String apiKey = "e2f4d8cb24ebe54b370e3f06a12f2dbb";
    private final String urlByName = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final String urlByCoord = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    /**
     * formats url for given service
     * */
    @Override
    public String getUrl(){
        String url = "";
        Location location  = getLocation();
        url = String.format(urlByName,location.getName(), apiKey);
        return url;
    }

    /**
     * maps params of json response to the Weather Object
     * */
    @Override
    public WeatherShot responseToWeather(String responseContent) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
        //Temperature
        double tempInKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        int temperature = (int)(tempInKelvin - 273.15);
        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        //InMeter
        int visibility = jsonObject.get("visibility").getAsInt();
        int clouds = jsonObject.getAsJsonObject("clouds").get("all").getAsInt();

        long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
        return WeatherShot.Builder.newBuilder()
                .date(dateTimestamp)
                .humidity(humidity)
                .temperature(temperature)
                .clouds(clouds)
                .windSpeed(windSpeed)
                .visibility(visibility)
                .build();// (dateTimestamp, humidity, temperature, windSpeed, clouds, visibility);
    }

        /*
    @Override
    public String getUrl(){
        String url = "";
        LocationCoord coord  = weatherServiceEngine.getLocationCoord();
        LocationName name = weatherServiceEngine.getLocationName();
        if(coord != null){
            url = String.format(urlByCoord,coord.getLat(),coord.getLon(), apiKey);
        }else if(name != null) {
            url = String.format(urlByName,name.getName(), apiKey);
        }
        return url;
    }
*/

}


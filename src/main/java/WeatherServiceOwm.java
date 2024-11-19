//package weather;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import weather.WeatherService;
import weather.WeatherServiceEngine;

import weather.entity.LocationCoord;
import weather.entity.LocationName;
import weather.entity.Weather;
import weather.exception.BadInputException;

import java.io.IOException;


public class WeatherServiceOwm extends WeatherServiceEngine implements WeatherService {

    private final String apiKey = "e2f4d8cb24ebe54b370e3f06a12f2dbb";
    private final String urlByName = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final String urlByCoord = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    private static WeatherServiceOwm weatherServiceEngine;// = getInstance();

    /**
     * Initializes and returns the WeatherServiceEngine singleton
     * */
    public static WeatherServiceOwm getInstance(){
        if(WeatherServiceOwm.weatherServiceEngine  == null){
            weatherServiceEngine  = new WeatherServiceOwm();
        }
        return weatherServiceEngine;
    }
    /**
     * Retrieves weather from current service
     * */
    @Override
    public Object weatherByLocation(String location) {
        try {
            WeatherServiceEngine weatherServiceEngine = WeatherServiceOwm.getInstance();
            weatherServiceEngine.initRequest(location);
            weatherServiceEngine.requestWeather();
            return mapWeatherToJson();

        }catch(BadInputException e){
        }catch(IOException e){
        }
        return null;
    }

    /**
     * formats url for given service
     * */
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

    /**
     * maps params of json response to the Weather Object
     * */
    @Override
    public Weather responseToWeather(String responseContent) {
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
        return new Weather(dateTimestamp, humidity, temperature, windSpeed, clouds, visibility);
    }

    /**
     * maps Weather object to JsonObject response
     * */

    @Override
    public String mapWeatherToJson() {
        Weather weather = getWeather();
        Gson gson = new Gson();
        return  gson.toJson(weather);
    }


}

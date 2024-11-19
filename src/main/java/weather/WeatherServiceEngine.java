package weather;

import jakarta.servlet.ServletException;
import weather.entity.LocationCoord;
import weather.entity.LocationName;
import weather.entity.Weather;
import weather.exception.BadInputException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class WeatherServiceEngine {


    WeatherServiceResponse weatherServiceResponse;
    WeatherServiceRequest weatherServiceRequest;
    /**
     * Initializes and validates Input Data for WeatherServiceEngine
     * */
    public void initRequest(String location) throws BadInputException {
        if(location == null || location.length() < 3){
            throw new BadInputException();
        }
        boolean isLonLat = Pattern.matches("^(-?\\d+\\.\\d+)(\\s*,\\s*)?(-?\\d+\\.\\d+)$", location);;
        if(isLonLat){
            String[] lonLat = location.split(",");
            weatherServiceRequest =  new WeatherServiceRequest(new LocationCoord(lonLat[0], lonLat[1]));
        }else{
            weatherServiceRequest =  new WeatherServiceRequest(new LocationName(location));
        }
    }

    /**
     * Get weather with given weatherServiceRequest. It would be nice to implement it in dedicated resource
     * */
    public void requestWeather() throws IOException {
        String apiUrl = getUrl();
        URL serviceUrl = new URL(apiUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) serviceUrl.openConnection();
        httpConnection.setRequestMethod("GET");
        InputStreamReader inputStreamReader = new InputStreamReader(httpConnection.getInputStream());

        Scanner scanner = new Scanner(inputStreamReader);
        StringBuilder strBuilder = new StringBuilder();
        while(scanner.hasNext()) {
            //reads and returns the next line to the string buffer.
            strBuilder.append(scanner.nextLine());
        }
        scanner.close();
        Weather weather =  responseToWeather(strBuilder.toString());
        weatherServiceResponse = new WeatherServiceResponse(weather);
    }

    public LocationCoord getLocationCoord(){
        return weatherServiceRequest.coord;
    }
    public LocationName getLocationName(){
        return weatherServiceRequest.name;
    }
    public Weather getWeather(){
        return weatherServiceResponse.getWeather();
    }

    public abstract String getUrl();

    public abstract Weather responseToWeather(String response);

    public abstract Object mapWeatherToJson();

}

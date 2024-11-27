package weather.core;

import weather.core.entity.Location;
import weather.core.exception.BadInputException;
import weather.core.entity.LocationCoord;
import weather.core.entity.LocationName;
import weather.core.entity.WeatherShot;

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
     */

    public void initRequest(Location location) throws BadInputException {
        weatherServiceRequest = new WeatherServiceRequest(location);
    }


    /*
    public void initRequest(String location) throws BadInputException {
        if (location == null || location.length() < 3) {
            throw new BadInputException();
        }
        boolean isLonLat = Pattern.matches("^(-?\\d+\\.\\d+)(\\s*,\\s*)?(-?\\d+\\.\\d+)$", location);
        ;
        if (isLonLat) {
            String[] lonLat = location.split(",");
            weatherServiceRequest = new WeatherServiceRequest(new LocationCoord(lonLat[0], lonLat[1]));
        } else {
            weatherServiceRequest = new WeatherServiceRequest(new LocationName(location));
        }
    }




*/

    /**
     * Retrieves weather from current service
     */
    public WeatherShot weatherByLocation(Location location) {
        try {
            initRequest(location);
            requestWeather();
            return getWeather();

        } catch (BadInputException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Get weather with given weatherServiceRequest.
     */
    public void requestWeather() throws IOException {
        String apiUrl = getUrl();
        URL serviceUrl = new URL(apiUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) serviceUrl.openConnection();
        httpConnection.setRequestMethod("GET");
        InputStreamReader inputStreamReader = new InputStreamReader(httpConnection.getInputStream());

        Scanner scanner = new Scanner(inputStreamReader);
        StringBuilder strBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            //reads and returns the next line to the string buffer.
            strBuilder.append(scanner.nextLine());
        }
        scanner.close();
        WeatherShot weather = responseToWeather(strBuilder.toString());

        weatherServiceResponse = new WeatherServiceResponse(weather);
    }

    public Location getLocation() {
        return weatherServiceRequest.location;
    }

    /*
        public LocationCoord getLocationCoord() {
            return weatherServiceRequest.coord;
        }

        public LocationName getLocationName() {
            return weatherServiceRequest.name;
        }
    */


    /*
    * returns the current WeatherShot which is the result of the last weather request
    * */
    public WeatherShot getWeather() {
        return weatherServiceResponse.getWeatherShot();
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
    public abstract String getUrl();

    public abstract WeatherShot responseToWeather(String response);

}

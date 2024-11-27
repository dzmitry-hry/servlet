package weather.app;

import weather.core.entity.*;

import java.util.List;
import java.util.Map;

public interface WeatherServiceManager {

    /**
     *   retrieves weather data from all meteoProviders which are serve the given location
     *   and saves it in DB
     * */
    List<WeatherShot> getWeather(String location);
    /**
     *  attaches given meteoStation to meteoProvider
     * */
    MeteoProvider attachMeteoStationToProvider(int meteoProviderId, int meteoStationId);
    /**
     *  removes meteoStation
     * */
    boolean removeMeteoStation(int meteoStationId);
    /**
     *  creats meteoStation with Location
     * */
    MeteoStation createMeteoStationWithLocation(String meteoStation, String location);

    /**
     *  creates meteoProvider
     * */
    MeteoProvider createMeteoProvider(String meteoProvider);


}

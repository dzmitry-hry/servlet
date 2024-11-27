package weather.repo;

import weather.repo.entity.Location;
import weather.repo.entity.MeteoProvider;
import weather.repo.entity.MeteoStation;
import weather.repo.entity.WeatherShot;

import java.util.List;
public interface WeatherRepository {
    /*
    *  persists result of global service request
    * */
    public void saveWeatherShot(WeatherShot weatherShot);
    /*
     *  checks if location with given name exists in DB and returns Object with ID
     * */
    public Location locationByName(Location location);
    /*
     *  retrieves from DB all meteoProviders which serve the given location
     * */
    public List<MeteoProvider> getMeteoProvidersByLocation(Location location);
    /*
     *  attach a new meteoStation to meteoProvider which serve given location
     * */
    public MeteoProvider attachMeteoStationToProvider(int meteoStationId, int meteoProviderId);
    /*
     *  delete from DB the given meteoStation
     * */
    public boolean deleteMeteoStation(int meteoStationId);
    /*
     *  Adds new meteoStation and location
     * */
    public MeteoStation createMeteoStationWithLocation(MeteoStation meteoStation);

    /*
     *  Adds new meteoProvider
     * */
    public MeteoProvider createMeteoProvider(MeteoProvider meteoprovider);


}

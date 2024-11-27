package weather.core;

import weather.core.entity.Location;
import weather.core.entity.WeatherShot;

public interface WeatherService {

  /*
  * weather core method makes requests to all meteoProviders which are implemented by the weather service
  * */
  WeatherShot weatherByLocation(Location location);
}

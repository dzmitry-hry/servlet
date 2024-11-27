package weather.core;

import weather.core.entity.Location;
import weather.core.entity.LocationCoord;
import weather.core.entity.LocationName;


/*
* Data Object for passing user request to weather service
* */
public class WeatherServiceRequest {

    Location location;
/*
    LocationName name;
    LocationCoord coord;

    public WeatherServiceRequest(LocationName name) {
        this.name = name;
    }

    public WeatherServiceRequest(LocationCoord coord) {
        this.coord = coord;
    }
*/
    public WeatherServiceRequest(Location location) {
        this.location = location;
    }



}

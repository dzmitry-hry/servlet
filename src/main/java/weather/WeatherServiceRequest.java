package weather;

import weather.entity.LocationCoord;
import weather.entity.LocationName;
import weather.exception.BadInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
* Data Object for passing user request to weather service
* */
public class WeatherServiceRequest {
    LocationName name;
    LocationCoord coord;

    public WeatherServiceRequest(LocationName name) {
        this.name = name;
    }

    public WeatherServiceRequest(LocationCoord coord) {
        this.coord = coord;
    }




}

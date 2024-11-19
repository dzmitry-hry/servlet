package weather;
import weather.entity.Weather;

public class WeatherServiceResponse {
    private final Weather weather;

    public WeatherServiceResponse(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}

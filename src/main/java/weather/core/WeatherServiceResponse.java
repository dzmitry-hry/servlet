package weather.core;
import weather.core.entity.WeatherShot;

public class WeatherServiceResponse {
    private final WeatherShot weatherShot;

    public WeatherServiceResponse(WeatherShot weatherShot) {
        this.weatherShot = weatherShot;
    }

    public WeatherShot getWeatherShot() {
        return weatherShot;
    }
}

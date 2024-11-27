package weather;

import weather.app.WeatherServiceManager;
import weather.app.WeatherServiceManagerImpl;
import weather.app.mapper.WeatherEntityMapper;
import weather.core.WeatherService;
import weather.repo.WeatherRepository;
import weather.repo.WeatherRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WeatherRunner {

    static private WeatherServiceManager weatherServiceManager;

    static public WeatherServiceManager getInstance() {
        if (weatherServiceManager == null) {
            try {
                WeatherRepository weatherRepository = new WeatherRepositoryImpl();
                WeatherEntityMapper weatherEntityMapper = new WeatherEntityMapper();
                Map<String, WeatherService> weatherServiceMap = new HashMap<>();
                weatherServiceMap.put("OpenWeatherMap1", new WeatherServiceOwmFirst());
                weatherServiceMap.put("OpenWeatherMap2", new WeatherServiceOwmSecond());
                weatherServiceManager = new WeatherServiceManagerImpl(weatherRepository, weatherServiceMap, weatherEntityMapper);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(weatherServiceManager);
        return weatherServiceManager;
    }

    static public WeatherServiceManager getInstance(WeatherRepository weatherRepository,
                                                    WeatherEntityMapper weatherEntityMapper,
                                                    Map<String, WeatherService> weatherServiceMap) {
        if (weatherServiceManager == null) {
            try {
                /*
                WeatherRepository weatherRepository = new WeatherRepositoryImpl();
                WeatherEntityMapper weatherEntityMapper = new WeatherEntityMapper();
                Map<String, WeatherService> weatherServiceMap = new HashMap<>();
                */
                weatherServiceMap.put("OpenWeatherMap1", new WeatherServiceOwmFirst());
                weatherServiceMap.put("OpenWeatherMap2", new WeatherServiceOwmSecond());
                weatherServiceManager = new WeatherServiceManagerImpl(weatherRepository, weatherServiceMap, weatherEntityMapper);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(weatherServiceManager);
        return weatherServiceManager;
    }


}

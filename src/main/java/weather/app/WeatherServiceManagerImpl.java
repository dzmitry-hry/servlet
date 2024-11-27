package weather.app;

import weather.WeatherServiceOwmFirst;
import weather.WeatherServiceOwmSecond;
import weather.app.mapper.WeatherEntityMapper;
import weather.core.WeatherService;
import weather.core.entity.Location;
import weather.core.entity.MeteoProvider;
import weather.core.entity.MeteoStation;
import weather.core.entity.WeatherShot;
import weather.core.exception.BadInputException;
import weather.repo.WeatherRepository;
import weather.repo.WeatherRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeatherServiceManagerImpl implements WeatherServiceManager {
    private WeatherRepository weatherRepository;
    private Map<String, WeatherService> weatherServiceMap;
    private WeatherEntityMapper weatherEntityMapper;

    public WeatherServiceManagerImpl(WeatherRepository weatherRepository, Map<String, WeatherService> weatherServiceMap, WeatherEntityMapper weatherEntityMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherServiceMap = weatherServiceMap;
        this.weatherEntityMapper = weatherEntityMapper;
    }

    @Override
    public List<WeatherShot> getWeather(String locationStr) {
        List<WeatherShot> weatherShotList = new ArrayList<>();
        try {
            Location locationCore = initLocation(locationStr);
            weather.repo.entity.Location location = weatherEntityMapper.locationCoreToRepo(locationCore);
            location = weatherRepository.locationByName(location);
            locationCore = weatherEntityMapper.locationRepoToCore(location);

            List<weather.repo.entity.MeteoProvider> meteoProviderRepoList = weatherRepository.getMeteoProvidersByLocation(location);
            List<MeteoProvider> meteoProviderCoreList = meteoProviderRepoList.stream().map(weatherEntityMapper::meteoProviderRepoToCore).collect(Collectors.toList());
            for (MeteoProvider meteoProvider : meteoProviderCoreList) {
                if (weatherServiceMap.containsKey(meteoProvider.getName())) {
                    WeatherService weatherService = weatherServiceMap.get(meteoProvider.getName());
                    WeatherShot weatherShot = weatherService.weatherByLocation(locationCore);
                    weatherShot.setLocation(locationCore);
                    weatherShot.setMeteoProvider(meteoProvider);
                    weatherRepository.saveWeatherShot(weatherEntityMapper.weatherShotCoreToRepo(weatherShot));
                    weatherShotList.add(weatherShot);
                }
            }

        } catch (BadInputException e) {
        }
        return weatherShotList;
    }

    @Override
    public MeteoProvider attachMeteoStationToProvider(int meteoProviderId, int meteoStationId) {
        weather.repo.entity.MeteoProvider meteoProvider = weatherRepository.attachMeteoStationToProvider(meteoStationId, meteoProviderId);
        return meteoProvider != null ? weatherEntityMapper.meteoProviderRepoToCore(meteoProvider) : null;
    }

    @Override
    public boolean removeMeteoStation(int meteoStationId){
        return weatherRepository.deleteMeteoStation(meteoStationId);
    }


    private Location initLocation(String locationStr) throws BadInputException {
        if (locationStr == null || locationStr.length() < 3) {
            throw new BadInputException();
        }
        Location location = new Location(locationStr);
        return location;
    }

    @Override
    public MeteoStation createMeteoStationWithLocation(String meteoStationStr, String locationStr){
        Location location = new Location(locationStr);
        MeteoStation meteoStation = MeteoStation.Builder.newBuilder()
                .meteoProviderList(new ArrayList<>())
                .name(meteoStationStr)
                .location(location)
                .build();
        weather.repo.entity.MeteoStation meteoStationRepo =  weatherRepository.
                createMeteoStationWithLocation(weatherEntityMapper.meteoStationCoreToRepo(meteoStation));

        return weatherEntityMapper.meteoStationRepoToCore(meteoStationRepo);
  }

    @Override
    public MeteoProvider createMeteoProvider(String meteoProviderStr) {
        MeteoProvider meteoProvider = MeteoProvider.Builder.newBuilder()
                .meteoStations(new ArrayList<>())
                .name(meteoProviderStr)
                .build();
        weather.repo.entity.MeteoProvider meteoProviderRepo =
                weatherRepository.createMeteoProvider(weatherEntityMapper.meteoProviderCoreToRepo(meteoProvider));
        return weatherEntityMapper.meteoProviderRepoToCore(meteoProviderRepo);
    }
}

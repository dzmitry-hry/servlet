package weather.app.mapper;

import org.hibernate.Hibernate;
import weather.core.entity.MeteoStation;
import weather.repo.entity.Location;
import weather.repo.entity.MeteoProvider;
import weather.repo.entity.WeatherShot;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherEntityMapper {

    public WeatherShot weatherShotCoreToRepo(weather.core.entity.WeatherShot weatherShot) {
        return WeatherShot.Builder.newBuilder()
                .id(weatherShot.getId())
                .date(new Timestamp(weatherShot.getDate()))
                .clouds(weatherShot.getClouds())
                .temperature(weatherShot.getTemperature())
                .humidity(weatherShot.getHumidity())
                .windSpeed(weatherShot.getWindSpeed())
                .visibility(weatherShot.getVisibility())
                .location(new Location(weatherShot.getLocation().getId(), weatherShot.getLocation().getName()))
                .meteoProvider(new MeteoProvider(weatherShot.getMeteoProvider().getId(), weatherShot.getMeteoProvider().getName(), new ArrayList<>()))
                .build();
    }

    public weather.core.entity.WeatherShot weatherShotRepoToCore(weather.repo.entity.WeatherShot weatherShot) {
        return weather.core.entity.WeatherShot.Builder.newBuilder()
                .id(weatherShot.id())
                .date(weatherShot.getDate().getTime())
                .clouds(weatherShot.getClouds())
                .temperature(weatherShot.getTemperature())
                .humidity(weatherShot.getHumidity())
                .windSpeed(weatherShot.getWindSpeed())
                .visibility(weatherShot.getVisibility())
                .location(new weather.core.entity.Location(weatherShot.getLocation().getId(), weatherShot.getLocation().getName()))
                .build();
    }

    public weather.repo.entity.MeteoStation meteoStationCoreToRepo(MeteoStation meteoStation) {
        return weather.repo.entity.MeteoStation.Builder.newBuilder()
                .id(meteoStation.getId())
                .name(meteoStation.getName())
                .location(new weather.repo.entity.Location(meteoStation.getLocation().getId(), meteoStation.getLocation().getName()))
                .meteoProviderList(meteoProviderListCoreToRepo(meteoStation.getMeteoProviderList()))
                .build();
    }

    public weather.core.entity.MeteoStation meteoStationRepoToCore(weather.repo.entity.MeteoStation meteoStation) {
        return weather.core.entity.MeteoStation.Builder.newBuilder()
                .id(meteoStation.getId())
                .name(meteoStation.getName())
                .location(new weather.core.entity.Location(meteoStation.getLocation().getId(), meteoStation.getLocation().getName()))
                .meteoProviderList(meteoProviderListRepoToCore(meteoStation.getMeteoProvidersIfInit()))
                .build();
    }

    public weather.repo.entity.MeteoProvider meteoProviderCoreToRepo(weather.core.entity.MeteoProvider meteoProvider) {
        return weather.repo.entity.MeteoProvider.Builder.newBuilder()
                .id(meteoProvider.getId())
                .name(meteoProvider.getName())
                .meteoStations(meteoStationListCoreToRepo(meteoProvider.getMeteoStations()))
                .build();
    }

    public weather.core.entity.MeteoProvider meteoProviderRepoToCore(weather.repo.entity.MeteoProvider meteoProvider) {
        return weather.core.entity.MeteoProvider.Builder.newBuilder()
                .id(meteoProvider.getId())
                .name(meteoProvider.getName())
                .meteoStations(meteoStationListRepoToCore(meteoProvider.getMeteoStationsIfInit()))
                .build();
    }

    public weather.core.entity.Location locationRepoToCore(weather.repo.entity.Location location) {
        return weather.core.entity.Location.Builder.newBuilder()
                .id(location.getId())
                .name(location.getName())
                .build();
    }

    public weather.repo.entity.Location locationCoreToRepo(weather.core.entity.Location location) {
        return weather.repo.entity.Location.Builder.newBuilder()
                .id(location.getId())
                .name(location.getName())
                .build();
    }


    private List<weather.repo.entity.MeteoStation> meteoStationListCoreToRepo(List<MeteoStation> meteoStationList) {
        return meteoStationList.stream().map(this::meteoStationCoreToRepo).collect(Collectors.toList());
    }

    private List<weather.core.entity.MeteoStation> meteoStationListRepoToCore(List<weather.repo.entity.MeteoStation> meteoStationList) {
        return meteoStationList.stream().map(this::meteoStationRepoToCore).collect(Collectors.toList());
    }

    private List<weather.repo.entity.MeteoProvider> meteoProviderListCoreToRepo(List<weather.core.entity.MeteoProvider> meteoProviderList) {
        return meteoProviderList.stream().map(this::meteoProviderCoreToRepo).collect(Collectors.toList());
    }

    private List<weather.core.entity.MeteoProvider> meteoProviderListRepoToCore(List<weather.repo.entity.MeteoProvider> meteoProviderList) {
        return meteoProviderList.stream().map(this::meteoProviderRepoToCore).collect(Collectors.toList());
    }


}

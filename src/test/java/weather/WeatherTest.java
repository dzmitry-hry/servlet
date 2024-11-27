
package weather;

import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import weather.app.WeatherServiceManager;
import weather.app.mapper.WeatherEntityMapper;
import weather.core.WeatherService;
import weather.core.entity.MeteoProvider;
import weather.core.entity.MeteoStation;
import weather.repo.WeatherRepository;
import weather.repo.WeatherRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

@Testcontainers
class WeatherTest {

    static private WeatherServiceManager weatherServiceManager;
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("weather")
            .withUsername("postgres")
            .withPassword("admin")
            .withInitScript("init-db-test.sql");

    @BeforeAll
    static void startDb() {
        Configuration c = new Configuration()
                .setProperty("hibernate.connection.driver_class", postgres.getDriverClassName())
                .setProperty("hibernate.connection.url", postgres.getJdbcUrl())
                .setProperty("hibernate.connection.username", postgres.getUsername())
                .setProperty("hibernate.connection.password", postgres.getPassword())
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        postgres.start();
        try {
            WeatherRepository weatherRepository = new WeatherRepositoryImpl(c);
            WeatherEntityMapper weatherEntityMapper = new WeatherEntityMapper();
            Map<String, WeatherService> weatherServiceMap = new HashMap<>();

            weatherServiceManager = WeatherRunner.getInstance(
                    weatherRepository,
                    weatherEntityMapper,
                    weatherServiceMap
            );

            MeteoProvider meteoProviderFirst = weatherServiceManager.createMeteoProvider("OpenWeatherMap1");
            MeteoProvider meteoProviderSecond = weatherServiceManager.createMeteoProvider("OpenWeatherMap2");

            MeteoStation meteoStationTokyo = weatherServiceManager.createMeteoStationWithLocation("ms_Tokyo", "Tokyo");
            MeteoStation meteoStationOslo = weatherServiceManager.createMeteoStationWithLocation("ms_Oslo", "Oslo");
            MeteoStation meteoStationSydney = weatherServiceManager.createMeteoStationWithLocation("ms_Sydney", "Sydney");
            MeteoStation meteoStationMoscow = weatherServiceManager.createMeteoStationWithLocation("ms_Moscow", "Moscow");
            MeteoStation meteoStationLondon = weatherServiceManager.createMeteoStationWithLocation("ms_London", "London");
            MeteoStation meteoStationMexico = weatherServiceManager.createMeteoStationWithLocation("ms_Mexico", "Mexico");

            weatherServiceManager.attachMeteoStationToProvider(meteoProviderFirst.getId(), meteoStationLondon.getId());
            weatherServiceManager.attachMeteoStationToProvider(meteoProviderFirst.getId(), meteoStationOslo.getId());
            weatherServiceManager.attachMeteoStationToProvider(meteoProviderFirst.getId(), meteoStationMexico.getId());
            weatherServiceManager.attachMeteoStationToProvider(meteoProviderSecond.getId(), meteoStationLondon.getId());
            weatherServiceManager.attachMeteoStationToProvider(meteoProviderSecond.getId(), meteoStationTokyo.getId());
            weatherServiceManager.getWeather("Oslo");
            weatherServiceManager.getWeather("Mexico");
            weatherServiceManager.getWeather("London");
        } catch (Exception e) {

        }

    }

    @Test
    void test() {
//        weatherServiceManager.removeMeteoStation()
    }
}
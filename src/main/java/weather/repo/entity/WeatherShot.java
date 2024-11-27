package weather.repo.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "weathershots")
public class WeatherShot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp date;
    private int humidity;
    private int temperature;
    private double windSpeed;
    private int clouds;
	private int visibility;
    @OneToOne
    @JoinColumn(name="meteoprovider_id")
    private MeteoProvider meteoProvider;
    @OneToOne
    @JoinColumn(name="location_id")
    private Location location;

    private WeatherShot(Builder builder) {
        id = builder.id;
        date = builder.date;
        humidity = builder.humidity;
        temperature = builder.temperature;
        windSpeed = builder.windSpeed;
        clouds = builder.clouds;
        visibility = builder.visibility;
        location = builder.location;
        meteoProvider = builder.meteoProvider;
    }
    public int id() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getClouds() {
        return clouds;
    }

    public int getVisibility() {
        return visibility;
    }


    public MeteoProvider getMeteoProvider() {
        return meteoProvider;
    }

    public Location getLocation() {
        return location;
    }

    public static final class Builder {
        private int id;
        private Timestamp date;
        private int humidity;
        private int temperature;
        private double windSpeed;
        private int clouds;
        private int visibility;
        private Location location;
        private MeteoProvider meteoProvider;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }


        public Builder id(int val) {
            id = val;
            return this;
        }


        public Builder date(Timestamp val) {
            date = val;
            return this;
        }

        public Builder humidity(int val) {
            humidity = val;
            return this;
        }

        public Builder temperature(int val) {
            temperature = val;
            return this;
        }

        public Builder windSpeed(double val) {
            windSpeed = val;
            return this;
        }

        public Builder clouds(int val) {
            clouds = val;
            return this;
        }

        public Builder visibility(int val) {
            visibility = val;
            return this;
        }

        public Builder location(Location val) {
            location = val;
            return this;
        }

        public Builder meteoProvider(MeteoProvider val) {
            meteoProvider = val;
            return this;
        }

        public WeatherShot build() {
            return new WeatherShot(this);
        }
    }
}

package weather.core.entity;

public class WeatherShot {

    private int id;
    private long date;
    private int humidity;
    private int temperature;
    private double windSpeed;
    private int clouds;
	private int visibility;
    private MeteoProvider meteoProvider;
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

    public long getDate() {
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

    public int getId() {
        return id;
    }

    public void setMeteoProvider(MeteoProvider meteoProvider) {
        this.meteoProvider = meteoProvider;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static final class Builder {

        private int id;
        private long date;
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

        public Builder date(long val) {
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
        public Builder visibility(MeteoProvider val) {
            meteoProvider = val;
            return this;
        }



        public WeatherShot build() {
            return new WeatherShot(this);
        }
    }
}

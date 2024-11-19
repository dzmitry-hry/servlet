package weather.entity;

public class Weather {

    private long date;
    private int humidity;
    private int temperature;
    private double windSpeed;
    private int clouds;
	private int visibility;

    public Weather(long date, int humidity, int temperature, double windSpeed, int clouds, int visibility) {
        this.date = date;
        this.humidity = humidity;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
        this.visibility = visibility;
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
}

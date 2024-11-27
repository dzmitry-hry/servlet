package weather.repo.entity;

public class LocationCoord {
    String lat;
    String lon;

    public LocationCoord(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}

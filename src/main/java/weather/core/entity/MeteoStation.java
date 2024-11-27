package weather.core.entity;
import java.util.List;

public class MeteoStation {
    private int id;
    private Location location;
    private String name;
    private List<MeteoProvider> meteoProviderList;

    public MeteoStation(int id, Location location, String name, List<MeteoProvider> meteoProviderList) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.meteoProviderList = meteoProviderList;
    }

    private MeteoStation(Builder builder) {
        id = builder.id;
        location = builder.location;
        name = builder.name;
        meteoProviderList = builder.meteoProviderList;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public List<MeteoProvider> getMeteoProviderList() {
        return meteoProviderList;
    }

    public void setMeteoProviderList(List<MeteoProvider> meteoProviderList) {
        this.meteoProviderList = meteoProviderList;
    }

    public static final class Builder {
        private int id;
        private Location location;
        private String name;
        private List<MeteoProvider> meteoProviderList;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder location(Location val) {
            location = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder meteoProviderList(List<MeteoProvider> val) {
            meteoProviderList = val;
            return this;
        }

        public MeteoStation build() {
            return new MeteoStation(this);
        }
    }
}

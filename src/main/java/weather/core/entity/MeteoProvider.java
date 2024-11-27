package weather.core.entity;

import java.util.List;

public class MeteoProvider {
    private int id;
    private String name;
    private List<MeteoStation> meteoStations;


    public MeteoProvider(int id, String name, List<MeteoStation> meteoStations) {
        this.id = id;
        this.name = name;
        this.meteoStations = meteoStations;
    }

    private MeteoProvider(Builder builder) {
        id = builder.id;
        name = builder.name;
        meteoStations = builder.meteoStations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MeteoStation> getMeteoStations() {
        return meteoStations;
    }


    public static final class Builder {
        private int id;
        private String name;
        private List<MeteoStation> meteoStations;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder meteoStations(List<MeteoStation> val) {
            meteoStations = val;
            return this;
        }

        public MeteoProvider build() {
            return new MeteoProvider(this);
        }
    }
}

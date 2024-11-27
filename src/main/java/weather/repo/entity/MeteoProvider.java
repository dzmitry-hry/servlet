package weather.repo.entity;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentBag;
//import org.hibernate.mapping.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meteoproviders")
public class MeteoProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name="meteoprovider_meteostations",
                joinColumns = @JoinColumn(name="meteoprovider_id"),
                inverseJoinColumns = @JoinColumn(name="meteostation_id"))
    private List<MeteoStation> meteoStations;

    public MeteoProvider(int id, String name, List<MeteoStation> meteoStations) {
        this.id = id;
        this.name = name;
        this.meteoStations = meteoStations;
    }

    public MeteoProvider() {
        super();
    }

    public MeteoProvider(Builder builder) {
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

    public List<MeteoStation> getMeteoStationsIfInit() {
        return Hibernate.isInitialized(meteoStations) ? meteoStations : new ArrayList<>();
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeteoStations(List<MeteoStation> meteoStations) {
        this.meteoStations = meteoStations;
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

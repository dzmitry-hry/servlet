package weather.repo.entity;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentBag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meteostations")
public class MeteoStation {
    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToMany
    @JoinTable(name="meteoprovider_meteostations",
            joinColumns = @JoinColumn(name="meteostation_id"),
            inverseJoinColumns = @JoinColumn(name="meteopovider_id"))
    private List<MeteoProvider> meteoProviders;
    private String name;

    public MeteoStation() {
    }

    public MeteoStation(int id, Location location, List<MeteoProvider> meteoProviderList, String name) {
        this.id = id;
        this.location = location;
        this.meteoProviders = meteoProviderList;
        this.name = name;
    }

    private MeteoStation(Builder builder) {
        setId(builder.id);
        setLocation(builder.location);
        meteoProviders = builder.meteoProviderList;
        setName(builder.name);
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public List<MeteoProvider> getMeteoProviders() {
        return meteoProviders;

    }

    public List<MeteoProvider> getMeteoProvidersIfInit() {
        return Hibernate.isInitialized(meteoProviders) ? meteoProviders : new ArrayList<>();

    }


    public void setMeteoProviders(List<MeteoProvider> meteoProviders) {
        this.meteoProviders = meteoProviders;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private int id;
        private Location location;
        private List<MeteoProvider> meteoProviderList;
        private String name;

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

        public Builder meteoProviderList(List<MeteoProvider> val) {
            meteoProviderList = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public MeteoStation build() {
            return new MeteoStation(this);
        }
    }
}

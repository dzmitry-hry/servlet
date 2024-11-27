package weather.repo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Location() {
        super();
    }

    public Location(int id, String name) {
        this.name = name;this.id = id;
    }
    public Location( String name) {
        this.name = name;
    }

    private Location(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private int id;
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

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}

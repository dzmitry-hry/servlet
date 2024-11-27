package weather.core.entity;

public class Location {
    int id;
    String name;


    public Location(String name) {
        this.name = name;
    }

    public Location(int id, String name) {
        this.name = name;this.id = id;
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

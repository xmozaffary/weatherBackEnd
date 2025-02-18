package se.ali.weatherbackend.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "coordinates")
public class CoordinatesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double lat;
    private double lon;
    private String country;

    public CoordinatesEntity(){};

    public CoordinatesEntity(Builder builder) {
        this.name = builder.name;
        this.lat = builder.lat;
        this.lon = builder.lon;
        this.country = builder.country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    //Builder
    public static class Builder{
        private String name;
        private double lat;
        private double lon;
        private String country;


        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder lat(double lat){
            this.lat = lat;
            return this;
        }

        public Builder lon(double lon){
            this.lon = lon;
            return this;
        }

        public Builder country(String country){
            this.country = country;
            return this;
        }

        public CoordinatesEntity build(){
            return new CoordinatesEntity(this);
        }
    }
}

package se.ali.weatherbackend.model;


import lombok.Data;

import java.util.Map;

@Data

public class CoordinatesResponse {
    private String name;
    private Map<String , String> localNames;
    private double lat;
    private double lon;
    private String country;
}

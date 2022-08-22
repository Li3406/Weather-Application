package com.example.fit2081weatherapp;

public class LocationWeather {
    // 4 weather conditions
    private String temp_c;    // temperature
    private String precip_mm;    // precipitation
    private String humidity;    // humidity
    private String wind_kph;    // wind

    // constructor
    public LocationWeather(String temp_c, String precip_mm, String humidity, String wind_kph) {
        this.temp_c = temp_c;
        this.precip_mm = precip_mm;
        this.humidity = humidity;
        this.wind_kph = wind_kph;
    }

    // getters for the weather conditions
    public String getTemp_c() {
        return temp_c;
    }

    public String getPrecip_mm() {
        return precip_mm;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    // setters for weather conditions
    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public void setPrecip_mm(String precip_mm) {
        this.precip_mm = precip_mm;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }
}

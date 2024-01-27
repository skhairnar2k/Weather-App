package com.siddhesh.weather;



import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private Weather[] weather;

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public static class Main {
        @SerializedName("temp")
        private double temp;

        public double getTemp() {
            return temp;
        }
    }

    public static class Weather {
        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}


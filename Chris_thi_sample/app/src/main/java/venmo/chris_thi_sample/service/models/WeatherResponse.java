package venmo.chris_thi_sample.service.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    public final City city;
    @SerializedName("list")
    public final List<Forecast> forecastList;

    public WeatherResponse(City city, List<Forecast> forecastList) {
        this.city = city;
        this.forecastList = forecastList;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city=" + city +
                ", forecastList=" + forecastList +
                '}';
    }
}

package venmo.chris_thi_sample.service;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import venmo.chris_thi_sample.service.models.WeatherResponse;

public interface OpenWeatherMapService {
    String API_URL = "http://api.openweathermap.org";
    String API_KEY = "b447c0f2fb73cd2b266de6ffd0ecc4b9";

    @GET("/data/2.5/forecast/daily")
    Call<WeatherResponse> getFiveDayWeather(@Query("id") int cityID, @Query("units") String
            units, @Query("cnd") int count, @Query("APPID") String apiKey);
}

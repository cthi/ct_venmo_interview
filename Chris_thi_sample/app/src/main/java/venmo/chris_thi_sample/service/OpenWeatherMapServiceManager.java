package venmo.chris_thi_sample.service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class OpenWeatherMapServiceManager {
    private final static OpenWeatherMapService mService;

    static {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(OpenWeatherMapService.API_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        mService = retrofit.create(OpenWeatherMapService.class);
    }

    private OpenWeatherMapServiceManager() {
    }

    public static OpenWeatherMapService getInstance() {
        return mService;
    }
}

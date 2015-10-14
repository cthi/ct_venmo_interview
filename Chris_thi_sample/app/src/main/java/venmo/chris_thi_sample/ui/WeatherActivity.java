package venmo.chris_thi_sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import venmo.chris_thi_sample.BuildConfig;
import venmo.chris_thi_sample.R;
import venmo.chris_thi_sample.adapter.ForecastAdapter;
import venmo.chris_thi_sample.service.OpenWeatherMapService;
import venmo.chris_thi_sample.service.OpenWeatherMapServiceManager;
import venmo.chris_thi_sample.service.models.Forecast;
import venmo.chris_thi_sample.service.models.WeatherResponse;
import venmo.chris_thi_sample.utils.NetworkUtils;

public class WeatherActivity extends AppCompatActivity {
    private static final int TOR_CITY_ID = 6167865;
    private static final String UNIT_IMPERIAL = "imperial";

    private static final String FORECAST_KEY = "forecasts";
    private static final String CITY_KEY = "city";
    private static final String COUNTRY_KEY = "country";

    @Bind(R.id.weather_rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.weather_tb)
    Toolbar mToolbar;
    @Bind(R.id.weather_error)
    TextView mErrorView;

    private List<Forecast> mForecasts;
    private String mCityName;
    private String mCityCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);

        initToolbar();
        initRv();

        if (null != savedInstanceState && savedInstanceState.containsKey(FORECAST_KEY)) {
            mForecasts = savedInstanceState.getParcelableArrayList(FORECAST_KEY);
            mCityCountry = savedInstanceState.getString(COUNTRY_KEY);
            mCityName = savedInstanceState.getString(CITY_KEY);

            updateWeatherData();
        } else {
            loadWeatherData();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (null != mForecasts) {
            outState.putParcelableArrayList(FORECAST_KEY, (ArrayList<Forecast>) mForecasts);
            outState.putString(COUNTRY_KEY, mCityCountry);
            outState.putString(CITY_KEY, mCityName);
        }
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadWeatherData() {
        if (!NetworkUtils.hasNetworkConnection(this)) {
            showErrorView(R.string.error_no_network);
        }

        Call<WeatherResponse> call = OpenWeatherMapServiceManager.getInstance().getFiveDayWeather
                (TOR_CITY_ID, UNIT_IMPERIAL, 7, OpenWeatherMapService.API_KEY);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Response<WeatherResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mForecasts = response.body().forecastList;
                    mCityCountry = response.body().city.country;
                    mCityName = response.body().city.name;

                    updateWeatherData();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (BuildConfig.DEBUG) {
                    t.printStackTrace();
                }
                showErrorView(R.string.error_other);
            }
        });
    }

    private void updateWeatherData() {
        mRecyclerView.setAdapter(new ForecastAdapter(mForecasts, WeatherActivity.this));

        String localizedWeatherTitle = getString(R.string.weather_title);
        setTitle(localizedWeatherTitle + " - " + mCityName + ", " + mCityCountry);
    }

    private void showErrorView(int stringId) {
        mErrorView.setText(getString(stringId));
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }
}

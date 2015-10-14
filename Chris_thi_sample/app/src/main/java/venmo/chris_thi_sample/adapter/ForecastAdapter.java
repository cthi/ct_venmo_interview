package venmo.chris_thi_sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import venmo.chris_thi_sample.R;
import venmo.chris_thi_sample.service.models.Forecast;
import venmo.chris_thi_sample.utils.DateUtils;
import venmo.chris_thi_sample.utils.TemperatureUtils;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastHolder> {
    private Context mContext;
    private List<Forecast> mForecasts;

    public ForecastAdapter(List<Forecast> forecasts, Context context) {
        this.mForecasts = forecasts;
        this.mContext = context;
    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast,
                parent, false);

        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastHolder holder, int position) {
        Forecast forecast = mForecasts.get(position);

        holder.date.setText(DateUtils.fancyDate(forecast.date));
        holder.high.setText(TemperatureUtils.fancyTemperature(forecast.temperatures.max, forecast
                .temperatures.min));
        holder.humidity.setText(TemperatureUtils.fancyHumidex(forecast.humidity));
        holder.status.setText(forecast.weatherConditions.get(0).description);
        Glide.with(mContext).load(TemperatureUtils.weatherIcon(forecast.weatherConditions.get(0)
                .icon)).into(holder.weatherImage);
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    static class ForecastHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.forecast_date)
        TextView date;
        @Bind(R.id.forecast_high)
        TextView high;
        @Bind(R.id.forecast_humidity)
        TextView humidity;
        @Bind(R.id.forecast_status)
        TextView status;
        @Bind(R.id.forecast_image)
        ImageView weatherImage;

        public ForecastHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}

package venmo.chris_thi_sample.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Forecast implements Parcelable {
    @SerializedName("dt")
    public final long date;
    @SerializedName("temp")
    public final Temperatures temperatures;
    public final int humidity;
    @SerializedName("weather")
    public final List<WeatherConditions> weatherConditions;

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", temperatures=" + temperatures +
                ", humidity='" + humidity + '\'' +
                ", weatherConditions=" + weatherConditions +
                '}';
    }

    public Forecast(long date, Temperatures temperatures, int humidity,
                    List<WeatherConditions> weatherConditions) {
        this.date = date;
        this.temperatures = temperatures;
        this.humidity = humidity;
        this.weatherConditions = weatherConditions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
        dest.writeParcelable(this.temperatures, flags);
        dest.writeInt(this.humidity);
        dest.writeList(this.weatherConditions);
    }

    protected Forecast(Parcel in) {
        this.date = in.readLong();
        this.temperatures = in.readParcelable(Temperatures.class.getClassLoader());
        this.humidity = in.readInt();
        this.weatherConditions = new ArrayList<WeatherConditions>();
        in.readList(this.weatherConditions, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}

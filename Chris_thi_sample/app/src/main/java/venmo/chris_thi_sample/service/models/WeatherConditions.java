package venmo.chris_thi_sample.service.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherConditions implements Parcelable {
    public final int id;
    public final String main;
    public final String description;
    public final String icon;

    public WeatherConditions(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherConditions{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.main);
        dest.writeString(this.description);
        dest.writeString(this.icon);
    }

    protected WeatherConditions(Parcel in) {
        this.id = in.readInt();
        this.main = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
    }

    public static final Parcelable.Creator<WeatherConditions> CREATOR = new Parcelable
            .Creator<WeatherConditions>() {
        public WeatherConditions createFromParcel(Parcel source) {
            return new WeatherConditions(source);
        }

        public WeatherConditions[] newArray(int size) {
            return new WeatherConditions[size];
        }
    };
}

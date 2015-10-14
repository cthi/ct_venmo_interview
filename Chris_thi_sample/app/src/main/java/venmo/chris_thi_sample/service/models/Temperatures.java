package venmo.chris_thi_sample.service.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Temperatures implements Parcelable {
    public final double min;
    public final double max;

    public Temperatures(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "Temperatures{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.min);
        dest.writeDouble(this.max);
    }

    protected Temperatures(Parcel in) {
        this.min = in.readDouble();
        this.max = in.readDouble();
    }

    public static final Parcelable.Creator<Temperatures> CREATOR = new Parcelable
            .Creator<Temperatures>() {
        public Temperatures createFromParcel(Parcel source) {
            return new Temperatures(source);
        }

        public Temperatures[] newArray(int size) {
            return new Temperatures[size];
        }
    };
}

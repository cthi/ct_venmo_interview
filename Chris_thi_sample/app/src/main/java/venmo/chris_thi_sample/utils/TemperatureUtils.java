package venmo.chris_thi_sample.utils;

import java.text.DecimalFormat;

public class TemperatureUtils {
    private final static DecimalFormat mDecimalFormat;

    static {
        mDecimalFormat = new DecimalFormat("##");
    }

    public static String weatherIcon(String weatherCode) {
        return "http://openweathermap.org/img/w/" + weatherCode + ".png";
    }

    public static String fancyTemperature(double high, double low) {
        return mDecimalFormat.format(high) + "\u00B0" + "/" + mDecimalFormat.format(low) + "\u00B0";
    }

    public static String fancyHumidex(int humidityPct) {
        return humidityPct + "% Humidity";
    }
}

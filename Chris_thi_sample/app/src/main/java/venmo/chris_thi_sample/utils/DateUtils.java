package venmo.chris_thi_sample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final SimpleDateFormat mFormatter;

    static {
        mFormatter = new SimpleDateFormat("E, MMM dd", Locale.getDefault());
    }

    public static String fancyDate(long unixDate) {
        // It is generally preferable to use joda-time, though in this instance
        // the usage is simple enough that the default date API is fine.
        Date date = new Date(unixDate * 1000);

        return mFormatter.format(date);
    }
}

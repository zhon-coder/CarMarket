package com.example.carmarket.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtil {

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String currentDateTime() {
        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(currentTimeMillis());
    }

    public static String timestampToString(long timestamp) {
        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(new Date(timestamp));
    }
}

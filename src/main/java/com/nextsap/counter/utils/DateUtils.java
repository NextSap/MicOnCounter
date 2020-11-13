package com.nextsap.counter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getFormat(String format) {
        return getFormat(format, getCurrentTimeMillis());
    }

    public static String getFormat(String format, long time) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date(time));
    }

}

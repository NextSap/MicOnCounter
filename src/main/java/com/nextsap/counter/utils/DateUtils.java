package com.nextsap.counter.utils;

import java.text.ParseException;
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

    public static long getTime(String date){
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String current_date = getFormat("dd/MM/yyyy ") + date;
        try {
            Date d = f.parse(current_date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isBetween(long value, long start, long end){
        return value >= start && value <= end;
    }

}

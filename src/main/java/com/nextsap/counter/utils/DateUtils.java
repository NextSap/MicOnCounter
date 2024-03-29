package com.nextsap.counter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class to manage date
 */
public class DateUtils {

    /**
     * Current time millis
     *
     * @return the current time
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Get the current time with a format
     *
     * @param format is the format
     * @return the current time parsed with the format
     */
    public static String getFormat(String format) {
        return getFormat(format, getCurrentTimeMillis());
    }

    /**
     * Get a date with a format
     *
     * @param format is the format
     * @param time   is the time
     * @return the time parsed with the format
     */
    public static String getFormat(String format, long time) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date(time));
    }

    /**
     * Get the time from a date
     *
     * @param date the date
     * @return the time
     */
    public static long getTime(String date) {
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

    /**
     * If the time is between x and y
     *
     * @param value the time
     * @param start first time
     * @param end   second time
     * @return a boolean
     */
    public static boolean isBetween(long value, long start, long end) {
        return value >= start && value <= end;
    }

    /**
     * Return if the line starts with a date like in a logfile
     *
     * @param line is the line to check
     * @return a boolean
     */
    public static boolean isLogDate(String line) {
        try {
            return line.charAt(0) == '[' && line.charAt(9) == ']' && line.charAt(3) == ':' && line.charAt(6) == ':' && (line.contains("⚔") || line.contains("[SkyWars] ")) && (!line.contains(" [CHAT] [Groupe] ") && !line.contains("->"));
        } catch (Exception e) {
            return false;
        }
    }
}

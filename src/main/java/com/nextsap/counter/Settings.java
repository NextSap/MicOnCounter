package com.nextsap.counter;

/**
 * Settings
 */
public class Settings {

    private final static String version = "v1";
    private final static String about = "For Mic!ON by NextSap";

    /**
     * @return Icon path
     */
    public static String getIconPath() {
        String iconPath = "C:\\Users\\${name}\\AppData\\Roaming\\MicOnCounter\\icon.png";
        return iconPath.replace("${name}", System.getProperty("user.name"));
    }

    /**
     * @return Log path
     */
    public static String getLogPath() {
        String path = "C:\\Users\\${name}\\AppData\\Roaming\\.az-client\\logs\\latest.log";
        return path.replace("${name}", System.getProperty("user.name"));
    }

    public static String getVersion() {
        return version;
    }

    public static String getAbout() {
        return about;
    }
}

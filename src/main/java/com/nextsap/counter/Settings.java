package com.nextsap.counter;


public class Settings {

    public static String getIconPath(String name) {
        String iconPath = "C:\\Users\\${name}\\AppData\\Roaming\\MicOnCounter\\icon.png";
        return iconPath.replace("${name}", name);
    }
}

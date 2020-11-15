package com.nextsap.counter.logger;

/**
 * An enum
 */
public enum FileType {

    MATCH("C:\\Users\\${name}\\AppData\\Roaming\\MicOnCounter\\MatchLogs.txt"),
    CURRENT("C:\\Users\\${name}\\AppData\\Roaming\\MicOnCounter\\CurrentLogs.txt");

    // Define attribute
    private final String path;

    /**
     * {@link FileType} Constructor
     *
     * @param path is the log path
     */
    FileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path.replace("${name}", System.getProperty("user.name"));
    }
}

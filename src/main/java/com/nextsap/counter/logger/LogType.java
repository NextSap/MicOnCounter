package com.nextsap.counter.logger;

/**
 * An enum
 */
public enum LogType {

    SUCCESS("Success"),
    INFORMATION("Information"),
    ERROR("Error");

    // Define attribute
    private final String name;

    /**
     * {@link LogType} Constructor
     *
     * @param name is the log's name
     */
    LogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

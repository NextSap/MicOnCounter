package com.nextsap.counter.logger;

public enum LogType {

    SUCCESS("Success"),
    INFORMATION("Information"),
    ERROR("Error");


    private String name;

    LogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.nextsap.counter.logger;

import com.nextsap.counter.utils.DateUtils;

public class Log {

    private long time;
    private LogType type;
    private String message;

    public Log(LogType type, String message) {
        this.time = DateUtils.getCurrentTimeMillis();
        this.type = type;
        this.message = message;
    }

    public void log() {
        StringBuilder log = new StringBuilder();
        log.append("[").append(DateUtils.getFormat("dd/MM/yy hh:mm:ss")).append("]  Log<").append(this.type.getName()).append("> => ");
        log.append(message);

        if (type.equals(LogType.ERROR))
            System.err.println(log);
        else
            System.out.println(log);
    }

    public static void create(LogType type, String message) {
        Log log = new Log(type, message);
        log.log();
    }

    public static void fatalError() {
        System.exit(0);
    }

}

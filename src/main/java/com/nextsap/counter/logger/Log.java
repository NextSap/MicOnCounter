package com.nextsap.counter.logger;

import com.nextsap.counter.utils.DateUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Log Manager
 */
public class Log {

    // Define attributes
    private final LogType logType;
    private final FileType fileType;
    private final String message;

    /**
     * {@link Log} Constructor
     *
     * @param logType  is the {@link LogType}
     * @param fileType is the {@link FileType}
     * @param message  is the current message
     */
    public Log(LogType logType, FileType fileType, String message) {
        this.logType = logType;
        this.fileType = fileType;
        this.message = message;
    }

    /**
     * Create a log
     *
     * @param logType  is the {@link LogType}
     * @param fileType is the {@link FileType}
     * @param message  is the current message
     */
    public static void create(LogType logType, FileType fileType, String message) {
        Log log = new Log(logType, fileType, message);
        log.log();
    }

    /**
     * Fatal error
     */
    public static void fatalError() {
        System.exit(0);
    }

    /**
     * The log's body
     */
    public void log() {
        try {
            StringBuilder log = new StringBuilder();
            log.append("[").append(DateUtils.getFormat("dd/MM/yy hh:mm:ss")).append("]  Log<").append(this.logType.getName()).append("> => ");
            log.append(this.message);

            if (logType.equals(LogType.ERROR))
                System.err.println(log);
            else
                System.out.println(log);
            log.append("\n");
            Files.write(Paths.get(this.fileType.getPath()), log.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
    }
}

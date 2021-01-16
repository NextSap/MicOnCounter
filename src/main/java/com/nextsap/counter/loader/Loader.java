package com.nextsap.counter.loader;

import com.nextsap.counter.Settings;
import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.logger.FileType;
import com.nextsap.counter.logger.Log;
import com.nextsap.counter.logger.LogType;
import com.nextsap.counter.utils.DateUtils;
import com.nextsap.counter.utils.SplitUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The loader
 */
public class Loader {

    // Define attribute
    public static boolean partyFinished;

    /**
     * Load the log file
     *
     * @return a {@link List} who contains file's lines
     */
    private static List<String> load(long start, long end) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Settings.getLogPath()), StandardCharsets.UTF_8);
            List<String> content = new ArrayList<>();

            for (String line : lines) {
                if (!DateUtils.isLogDate(line)) continue;

                long date = DateUtils.getTime(line.split("\\[")[1].split("]")[0]);
                if (DateUtils.isBetween(date, start, end))
                    content.add(line);
            }
            return content;
        } catch (Exception e) {
            Log.create(LogType.ERROR, FileType.CURRENT, "Une erreur est survenue :");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse the log to create a {@link CustomGame}
     *
     * @param start is the time when the game starts
     * @param end   is the time when the game ends
     * @return a new {@link CustomGame}
     */
    public static CustomGame parser(CustomGame customGame, long start, long end) {
        List<String> content = load(start, end);

        if (content == null) {
            Log.create(LogType.ERROR, FileType.CURRENT, "Logs are null");
            Log.create(LogType.ERROR, FileType.MATCH, "Logs are null");
            return customGame;
        }

        content.forEach(log -> partyFinished = log.contains("[SkyWars] ") && log.contains(" a gagné !"));

        customGame.setTime(start, end);

        customGame.setLogs(content);

        SplitUtils splitUtils = new SplitUtils(customGame);

        for (String line : content) {
            splitUtils.addWinner(line);
            splitUtils.addPodium(line);
        }

        for (String line : content)
            splitUtils.addKiller(line);

        for (String killer : customGame.getKillers())
            customGame.addKill(killer);

        return customGame;
    }
}

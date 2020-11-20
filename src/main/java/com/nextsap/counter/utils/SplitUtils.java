package com.nextsap.counter.utils;

import com.nextsap.counter.customer.CustomGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitUtils {

    public static void addPodium(String line, CustomGame customGame) {
        if (line.contains(" a été tué par "))
            customGame.addPodium(line.split("⚔ ")[1].split(" a été tué par ")[0]);
        if (line.contains(" est mort."))
            customGame.addPodium(line.split("⚔ ")[1].split(" est mort.")[0]);
    }

    public static void addWinner(String line, CustomGame customGame) {
        if (line.contains("[SkyWars] ") && line.contains(" a gagné !"))
            customGame.addPodium(line.split("\\[SkyWars] ")[1].split(" ")[0]);
    }

    public static String getCurrentKillers(String line) {
        return line.split(" a été tué par ")[1].substring(0, line.split(" a été tué par ")[1].length() - 1);
    }

    public static void addKiller(String line, CustomGame customGame) {
        List<String> killers = new ArrayList<>();
        if (line.contains(" a été tué par ")) {
            String current = SplitUtils.getCurrentKillers(line);

            if (current.contains(" et ")) {

                if (current.contains(", ")) {
                    String[] killersTable = current.split(", ");

                    for (int i = 0; i < killersTable.length; i++) {
                        if (i == killersTable.length - 1) {
                            killers.addAll(Arrays.asList(killersTable[i].split(" et ")));
                            return;
                        }
                        killers.add(killersTable[i]);
                    }
                    return;
                }
                killers.addAll(Arrays.asList(current.split(" et ")));
                return;
            }
            killers.add(current);

            for (String killer : killers)
                customGame.addOneKill(killer);
        }
    }
}

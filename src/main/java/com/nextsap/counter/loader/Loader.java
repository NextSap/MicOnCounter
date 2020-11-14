package com.nextsap.counter.loader;

import com.nextsap.counter.Settings;
import com.nextsap.counter.customer.CustomParty;
import com.nextsap.counter.utils.DateUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Loader {

    public static boolean partyFinished;

    private static List<String> load() {
        try {
            File file = new File(Settings.getLogPath());
            Scanner scanner = new Scanner(file);
            List<String> content = new ArrayList<>();
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
            scanner.close();
            return content;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public static CustomParty parser(long start, long end) {
        List<String> parsed = new ArrayList<>();
        for (String s : load()) {
            System.out.println(s);
            long date = DateUtils.getTime(s.split("\\[")[1].split("]")[0]);
            if (DateUtils.isBetween(date, start, end) && (s.contains("⚔") || s.contains("[SkyWars] ")) && (!s.contains(" [CHAT] [Groupe] ") && !s.contains("->")))
                parsed.add(s);
        }

        parsed.forEach(log -> {
            partyFinished = log.contains("[SkyWars] ") && log.contains(" a gagné !");
        });

        CustomParty customParty = new CustomParty();
        customParty.setStart(start);
        customParty.setEnd(end);

        for (String line : parsed) {
            if (line.contains("[SkyWars] ") && line.contains(" a gagné !")) {
                customParty.addPodium(line.split("\\[SkyWars] ")[1].split(" ")[0]);
            }

            List<String> killers = new ArrayList<>();

            if (line.contains(" a été tué par ")) {
                customParty.addPodium(line.split("⚔ ")[1].split(" a été tué par ")[0]);
                String current = line.split(" a été tué par ")[1].substring(0, line.split(" a été tué par ")[1].length() - 1);

                if (current.contains(" et ")) {

                    if (current.contains(", ")) {
                        String[] table = current.split(", ");
                        for (int i = 0; i < table.length; i++) {
                            if (i == table.length - 1) {
                                killers.addAll(Arrays.asList(table[i].split(" et ")));
                            } else {
                                killers.add(table[i]);
                            }
                        }
                    } else {
                        killers.addAll(Arrays.asList(current.split(" et ")));
                    }
                } else {
                    killers.add(current);
                }
            }
            if (line.contains(" est mort."))
                customParty.addPodium(line.split("⚔ ")[1].split(" est mort.")[0]);

            for (String killer : killers)
                customParty.addOneKill(killer);
        }

        List<String> newPodium = new ArrayList<>();
        for (int i = customParty.getPodium().size() - 1; i >= 0; i--)
            newPodium.add(customParty.getPodium().get(i));

        customParty.setPodium(newPodium);
        return customParty;
    }
}

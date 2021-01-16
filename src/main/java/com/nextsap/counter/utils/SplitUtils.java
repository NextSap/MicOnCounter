package com.nextsap.counter.utils;

import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.customer.PodiumEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SplitUtils {

    private final CustomGame customGame;
    private final List<String> players;
    private final List<String> currentPlayers;

    public SplitUtils(CustomGame customGame) {
        this.customGame = customGame;
        this.players = customGame.getPlayers();
        this.currentPlayers = new ArrayList<>();
    }

    public void addPodium(String line) {
        if (line.contains(" a été tué par ")) {
            String player = line.split("⚔ ")[1].split(" a été tué par ")[0].toLowerCase();
            if (players.contains(player) && !currentPlayers.contains(player)) {
                customGame.addPodium(player, new PodiumEntry(players.size() - currentPlayers.size()));
                currentPlayers.add(player);
            }
        }
        if (line.contains(" est mort.")) {
            String player = line.split("⚔ ")[1].split(" est mort.")[0].toLowerCase();
            if (players.contains(player) && !currentPlayers.contains(player)) {
                customGame.addPodium(player, new PodiumEntry(players.size() - currentPlayers.size()));
                currentPlayers.add(player);
            }
        }
    }

    public void addWinner(String line) {
        if (line.contains("[SkyWars] ") && line.contains(" a gagné !") && currentPlayers.size() == players.size()) {
            String player = line.split("\\[SkyWars] ")[1].split(" ")[0].toLowerCase();
            if (players.contains(player) && !currentPlayers.contains(player)) {
                customGame.addPodium(player, new PodiumEntry(1));
                currentPlayers.add(player);
            }
        }
        if (line.contains("[SkyWars] ") && line.contains(" a gagné !") && currentPlayers.size() != players.size()) {
            String player = line.split("\\[SkyWars] ")[1].split(" ")[0].toLowerCase();
            if (players.contains(player) && !currentPlayers.contains(player)) {
                customGame.addPodium(player, new PodiumEntry(1));
                currentPlayers.add(player);
            }
            for (String currentPlayer : players) {
                if (!currentPlayers.contains(currentPlayer)) {
                    customGame.addPodium(currentPlayer, new PodiumEntry(2));
                    currentPlayers.add(player);
                }
            }
        }
    }

    public void addKiller(String line) {
        if (line.contains(" a été tué par ")) {
            String player = line.split("⚔ ")[1].split(" a été tué par ")[0].toLowerCase();
            String current = line.split(" a été tué par ")[1].substring(0, line.split(" a été tué par ")[1].length() - 1).toLowerCase();

            if (current.contains(player))
                current = current.replace(player, "");


            if (current.contains(" et ")) {
                if (current.contains(", ")) {
                    String[] killersTable = current.split(", ");

                    for (int i = 0; i < killersTable.length; i++) {
                        if (i == killersTable.length - 1) {
                            customGame.addKiller(killersTable[i].split(" et "));
                            return;
                        }
                        customGame.addKiller(killersTable[i]);
                    }
                    return;
                }
                customGame.addKiller(current.split(" et "));
                return;
            }
            customGame.addKiller(current);
        }
    }
}
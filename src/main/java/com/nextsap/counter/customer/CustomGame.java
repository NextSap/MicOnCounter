package com.nextsap.counter.customer;

import java.util.*;

/**
 * It is a Custom Skywars Game
 */
public class CustomGame {

    // Define attributes
    private List<String> players;
    private List<String> killers;
    private List<String> logs;
    private Map<String, PodiumEntry> podium;
    private long start;
    private long end;

    public CustomGame() {
        this.players = new ArrayList<>();
        this.killers = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.podium = new HashMap<>();
        this.start = 0;
        this.end = 0;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public void addPlayer(String... players) {
        this.players.addAll(Arrays.asList(players));
    }

    public void removePlayer(String... players) {
        this.players.removeAll(Arrays.asList(players));
    }

    public void removePlayer(String player) {
        this.players.remove(player);
    }

    public List<String> getKillers() {
        return killers;
    }

    public void setKillers(List<String> killers) {
        this.killers = killers;
    }

    public void addKiller(String... killers) {
        this.killers.addAll(Arrays.asList(killers));
    }

    public void removeKiller(String... killers) {
        this.killers.removeAll(Arrays.asList(killers));
    }

    public void removeKiller(String killers) {
        this.killers.remove(killers);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public Map<String, PodiumEntry> getPodium() {
        return podium;
    }

    public void addKill(String name) {
        if (podium.containsKey(name))
            podium.get(name).addKill();
    }

    public void addPodium(String name, PodiumEntry podiumEntry) {
        podium.put(name, podiumEntry);
    }

    public void removePodium(String name) {
        podium.remove(name);
    }

    public void setPodium(Map<String, PodiumEntry> podium) {
        this.podium = podium;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setTime(long start, long end) {
        this.start = start;
        this.end = end;
    }
}

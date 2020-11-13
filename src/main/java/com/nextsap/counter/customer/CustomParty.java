package com.nextsap.counter.customer;

import java.util.*;

public class CustomParty {

    private List<String> podium; // Name and place
    private Map<String, Integer> kills; // Name and amount of kill

    private long start;
    private long end;

    public CustomParty(List<String> podium, Map<String, Integer> kills, long start, long end) {
        this.podium = podium;
        this.kills = kills;
        this.start = start;
        this.end = end;
    }

    public CustomParty() {
        this.podium = new ArrayList<>();
        this.kills = new HashMap<>();
        this.start = 0;
        this.end = 0;
    }

    public List<String> getPodium() {
        return podium;
    }

    public void setPodium(List<String> podium) {
        this.podium = podium;
    }

    public void addPodium(String... name) {
        this.podium.addAll(Arrays.asList(name));
    }

    public void removePodium(String... name) {
        this.podium.removeAll(Arrays.asList(name));
    }

    public Map<String, Integer> getKills() {
        return kills;
    }

    public void setKills(Map<String, Integer> kills) {
        this.kills = kills;
    }

    public int getKills(String name) {
        int amountKill = 0;
        if (kills.get(name) != null) {
            amountKill = kills.get(name);
        }
        return amountKill;
    }

    public void addOneKill(String name) {
        int amountKill = 0;
        if (this.kills.get(name) != null) {
            amountKill = this.kills.get(name);
        }
        this.kills.put(name, amountKill + 1);
    }

    public void removeOneKill(String name) {
        int amountKill = 0;
        if (this.kills.get(name) != null) {
            amountKill = this.kills.get(name);
        }
        this.kills.put(name, amountKill - 1);
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
}

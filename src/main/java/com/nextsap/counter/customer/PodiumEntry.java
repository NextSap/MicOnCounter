package com.nextsap.counter.customer;

public class PodiumEntry {

    private int kills;
    private int podium;

    public PodiumEntry(int kills, int podium) {
        this.kills = kills;
        this.podium = podium;
    }

    public PodiumEntry(int podium){
        this.kills = 0;
        this.podium = podium;
    }

    public PodiumEntry() {
        this.kills = 0;
        this.podium = 0;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill() {
        this.kills++;
    }

    public int getPodium() {
        return podium;
    }

    public void setPodium(int podium) {
        this.podium = podium;
    }
}

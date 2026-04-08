package com.fightdemo.combat.dto;

public class HealthResponse {
    public String characterId;
    public int currentHp;
    public int maxHp;
    public boolean isDead;

    public HealthResponse(String Id, int current, int max, boolean dead) {
        this.characterId = Id;
        this.currentHp = current;
        this.maxHp = max;
        this.isDead = dead;
    }
}

package com.fightdemo.combat.dto;

public class AttackRequest {
    public String targetId;
    public String attackerId;
    public int comboCount; //for combo attacks, 0 for normal attacks
}

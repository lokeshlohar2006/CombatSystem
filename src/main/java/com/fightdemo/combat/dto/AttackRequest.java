package com.fightdemo.combat.dto;

public class AttackRequest {
    public String targetId;
    public String attackerId;
    public int comboCount; //for combo attacks, 0 for normal attacks

    // Getters
    public String getAttackerId() { return attackerId; }
    public String getTargetId() { return targetId; }
    public int getComboCount() { return comboCount; }

    // Setters
    public void setAttackerId(String attackerId) { this.attackerId = attackerId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }
    public void setComboCount(int comboCount) { this.comboCount = comboCount; }
}

package com.fightdemo.combat.model;

public interface Damageable {
    void takeDamage(int amount);
    void heal(int amount);
    boolean isDead();
    int getCurrentHp();
    int getMaxHp();
}
    


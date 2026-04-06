package com.fightdemo.combat.model;

public class Enemy extends GameCharacter {
    private final int armor; // to reduce incoming damage
    public Enemy(String characterID, int maxHP, int armor){
        super(characterID, maxHP);
        this.armor=armor;
    }

    @Override
    public void takeDamage(int amount){
        int reduced = Math.max(0, amount-armor);
        setCurrentHp(getCurrentHp()-reduced);
    }

    public int getArmor(){
        return armor;
    }
}
    

package com.fightdemo.combat.model;

public class CountessPlayer extends GameCharacter {
    private int comboCount;

    public CountessPlayer(String characterID, int maxHP) {
        super(characterID, maxHP);
        this.comboCount = 0;
    }

    @Override
    public void takeDamage(int amount){
        setCurrentHp(getCurrentHp()-amount);
    }

    public double getComboMultiplier(){
        return switch (comboCount) {
            case 1 -> 1.0;
            case 2 -> 1.5;
            case 3 -> 2.0;
            default -> 1.0;
        };
    }

    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }
    public int getComboCount() {
        return comboCount;
    }

}

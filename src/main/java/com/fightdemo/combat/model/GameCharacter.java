package com.fightdemo.combat.model;

public abstract class GameCharacter implements Damageable{
    private final String characterId;
    private final int maxHp;
    private int currentHp;

    public GameCharacter(String characterId, int maxHp) {
        this.characterId = characterId;
        this.maxHp = maxHp;
        this.currentHp = maxHp;  // starting at full HP
    }

    // Controlled access — to not set HP to -9999 lol
    protected void setCurrentHp(int hp) {
        this.currentHp = Math.max(0, Math.min(hp, maxHp));
    }

    @Override
    public int getCurrentHp() { return currentHp; }

    @Override
    public int getMaxHp() { return maxHp; }

    @Override
    public boolean isDead() { return currentHp <= 0; }

    @Override
    public void heal(int amount) {
        if (!isDead()) setCurrentHp(currentHp + amount);
    }

    public String getCharacterId() { return characterId; }

    // Forces subclasses to define their OWN damage logic
    @Override
    public abstract void takeDamage(int amount);
}

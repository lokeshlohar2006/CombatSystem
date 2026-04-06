package com.fightdemo.combat.service;

import com.fightdemo.combat.model.*;
import com.fightdemo.combat.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CombatService {
    private final CharacterRepository repo;
    public CombatService(CharacterRepository repo) {
        this.repo = repo;
    }

    //Polymorphism - target can be Enemy or CountessPlayer, doesn't matter
    public int applyDamage(String targetId, int rawDamage) {
        Damageable target  = repo.findById(targetId)
                .orElseThrow(() -> new RuntimeException("Character not found: " + targetId));
        target.takeDamage(rawDamage); //calls Enemy's or Player's version at runtime
        return target.getCurrentHp();
    }

    //when CountessPlayer lands a combo hit
    public int comboAttack(String playerId, String targetId){
        CountessPlayer player = (CountessPlayer) repo.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        int baseDamage = 25;
        int finalDamage = (int)(baseDamage * player.getComboMultiplier());
        return applyDamage(targetId, finalDamage);
    }

    public int heal(String targetId, int amount) {
        Damageable target = repo.findById(targetId)
                .orElseThrow(() -> new RuntimeException("Character not found: " + targetId));
        target.heal(amount);
        return target.getCurrentHp();
    }
}

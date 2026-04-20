package com.fightdemo.combat.controller;

import com.fightdemo.combat.dto.*;
import com.fightdemo.combat.model.*;
import com.fightdemo.combat.repository.CharacterRepository;
import com.fightdemo.combat.service.CombatService;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CombatController {
    
    private final CombatService combatService;
    private final CharacterRepository repo;

    public CombatController(CombatService combatService, CharacterRepository repo) {
        this.combatService = combatService;
        this.repo = repo;
    }

    // UE5 calls this when CountessPlayer lands a combo hit 
    @PostMapping("/attack")
    public ResponseEntity<HealthResponse> attack(@RequestBody AttackRequest req){
        //Update player's combo count first
        CountessPlayer player = (CountessPlayer) repo.findById(req.attackerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        player.setComboCount(req.comboCount);

        int newHp = combatService.comboAttack(req.attackerId, req.targetId);

        GameCharacter target = (GameCharacter) repo.findById(req.targetId).get();
        return ResponseEntity.ok(
            new HealthResponse(req.targetId, newHp, target.getMaxHp(), target.isDead())
        );
    }

    //UE5 calls this when enemy attacks the player
    @PostMapping("/take-damage")
    public ResponseEntity<HealthResponse> takeDamage(@RequestBody AttackRequest req){
        int newHp = combatService.applyDamage(req.targetId, req.comboCount);

        GameCharacter target = (GameCharacter) repo.findById(req.targetId).get();
        return ResponseEntity.ok(
            new HealthResponse(req.targetId, newHp, target.getMaxHp(), target.isDead())
        );
    }
    @PostMapping("/spawn/enemy")
    public ResponseEntity<Map<String, String>> spawnEnemy() {
        String id = UUID.randomUUID().toString();
        repo.save(new Enemy(id, 80, 5));
        return ResponseEntity.ok(Map.of("enemyId", id));
    }

    //UE5 polls this to sync HP on screen
    @GetMapping("/health/{id}")
    public ResponseEntity<HealthResponse> getHealth(@PathVariable String id){
        GameCharacter c = (GameCharacter) repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Character not found"));
        return ResponseEntity.ok(
            new HealthResponse(id, c.getCurrentHp(), c.getMaxHp(), c.isDead())
        );
    }

    @DeleteMapping("/enemy/{id}")
    public ResponseEntity<Void> removeEnemy(@PathVariable String id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }
}

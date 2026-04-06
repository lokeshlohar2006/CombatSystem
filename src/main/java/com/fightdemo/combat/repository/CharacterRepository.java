package com.fightdemo.combat.repository;

import com.fightdemo.combat.model.GameCharacter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CharacterRepository {
    private final Map<String, GameCharacter> store = new HashMap<>();

    public void save(GameCharacter character) {
        store.put(character.getCharacterId(), character);
    }

    public Optional<GameCharacter> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

}

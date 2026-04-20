package com.fightdemo.combat;

import com.fightdemo.combat.model.*;
import com.fightdemo.combat.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CharacterRepository repo;

    public DataSeeder(CharacterRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args){
        repo.save(new CountessPlayer("player_1", 100));
        System.out.println("Characters seeded");
    }
}


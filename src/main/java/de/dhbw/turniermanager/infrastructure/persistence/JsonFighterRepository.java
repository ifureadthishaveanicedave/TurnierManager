package de.dhbw.turniermanager.infrastructure.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.fighter.Fighter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFighterRepository implements FighterRepository {
    private final File file;
    private final ObjectMapper objectMapper;

    public JsonFighterRepository(String filePath) {
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(Fighter fighter) {
        List<Fighter> fighters = findAll();
        fighters.add(fighter);
        try {
            objectMapper.writeValue(file, fighters);
        } catch (IOException e) {
            System.err.println("Kämpfer speichern fehlgeschlagen: " + e.getMessage());
        }
    }

    @Override
    public List<Fighter> findAll() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Fighter>>() {});
        } catch (IOException e) {
            System.err.println("Kämpfer lesen fehlgeschlagen: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
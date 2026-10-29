package de.dhbw.turniermanager.infrastructure.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTournamentRepository implements TournamentRepository {
    private final File file;
    private final ObjectMapper objectMapper;

    public JsonTournamentRepository(String filePath) {
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(Tournament tournament) {
        List<Tournament> tournaments = findAll();
        tournaments.add(tournament);
        try {
            objectMapper.writeValue(file, tournaments);
        } catch (IOException e) {
            System.err.println("Speichern fehlgeschlagen: " + e.getMessage());
        }
    }

    @Override
    public List<Tournament> findAll() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Tournament>>() {});
        } catch (IOException e) {
            System.err.println("Lesen fehlgeschlagen: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Tournament updatedTournament) {
        List<Tournament> tournaments = findAll();
        tournaments.removeIf(t -> t.getName().equals(updatedTournament.getName()));
        tournaments.add(updatedTournament);
        try {
            objectMapper.writeValue(file, tournaments);
        } catch (IOException e) {
            System.err.println("Update fehlgeschlagen: " + e.getMessage());
        }
    }
    @Override
    public void delete(String tournamentName) {
        List<Tournament> tournaments = findAll();
        boolean removed = tournaments.removeIf(t -> t.getName().equals(tournamentName));

        if (removed) {
            try {
                objectMapper.writeValue(file, tournaments);
            } catch (IOException e) {
                System.err.println("Löschen fehlgeschlagen: " + e.getMessage());
            }
        }
    }
}
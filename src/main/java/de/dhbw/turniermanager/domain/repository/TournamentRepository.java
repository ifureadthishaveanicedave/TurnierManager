package de.dhbw.turniermanager.domain.repository;

import de.dhbw.turniermanager.domain.tournament.Tournament;
import java.util.List;

public interface TournamentRepository {
    void save(Tournament tournament);
    List<Tournament> findAll();
    void update(Tournament updatedTournament);
}
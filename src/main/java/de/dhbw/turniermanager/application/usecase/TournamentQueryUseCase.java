package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.domain.repository.TournamentRepository;

public class TournamentQueryUseCase {
    private final TournamentRepository tournamentRepo;

    public TournamentQueryUseCase(TournamentRepository tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public boolean tournamentExists(String name) {
        return tournamentRepo.findAll().stream()
                .anyMatch(t -> t.getName().equals(name));
    }
}
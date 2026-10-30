package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.TournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class CreateTournamentUseCase implements TournamentInputBoundary {
    private final TournamentOutputBoundary outputBoundary;
    private final TournamentRepository repository;

    public CreateTournamentUseCase(TournamentOutputBoundary outputBoundary, TournamentRepository repository) {
        this.outputBoundary = outputBoundary;
        this.repository = repository;
    }

    @Override
    public void createTournament(String name, String weightClass) {
        boolean exists = repository.findAll().stream().anyMatch(t -> t.getName().equals(name));
        if (exists) {
            outputBoundary.presentError("Ein Turnier mit dem Namen '" + name + "' existiert bereits.");
            return;
        }
        Tournament tournament = new Tournament(name, weightClass);
        repository.save(tournament);
        outputBoundary.presentTournamentCreated(tournament.getName());
    }
}
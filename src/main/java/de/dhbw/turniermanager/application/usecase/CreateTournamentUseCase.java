package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.TournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class CreateTournamentUseCase implements TournamentInputBoundary {
    private final TournamentOutputBoundary outputBoundary;

    public CreateTournamentUseCase(TournamentOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createTournament(String name) {
        Tournament tournament = new Tournament(name);
        outputBoundary.presentTournamentCreated(tournament.getName());
    }
}

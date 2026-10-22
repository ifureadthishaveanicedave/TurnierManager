package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewTournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewTournamentOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class ViewTournamentUseCase implements ViewTournamentInputBoundary {
    private final ViewTournamentOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public ViewTournamentUseCase(ViewTournamentOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void viewTournament(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Das Turnier '" + tournamentName + "' existiert nicht.");
            return;
        }

        outputBoundary.presentTournament(tournament.getName(), tournament.getRegisteredFighters());
    }
}
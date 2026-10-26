package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewMatchesInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class ViewMatchesUseCase implements ViewMatchesInputBoundary {
    private final ViewMatchesOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public ViewMatchesUseCase(ViewMatchesOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void viewMatches(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        if (tournament.getMatches().isEmpty()) {
            outputBoundary.presentError("Es gibt aktuell keine aktiven Kämpfe in diesem Turnier.");
            return;
        }

        outputBoundary.presentMatches(tournament.getName(), tournament.getMatches());
    }
}
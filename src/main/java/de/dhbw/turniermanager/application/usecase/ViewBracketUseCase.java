package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewBracketInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewBracketOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class ViewBracketUseCase implements ViewBracketInputBoundary {
    private final ViewBracketOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public ViewBracketUseCase(ViewBracketOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void viewBracket(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        outputBoundary.presentBracket(tournament.getName(), tournament.getPreviousRounds(), tournament.getMatches());
    }
}
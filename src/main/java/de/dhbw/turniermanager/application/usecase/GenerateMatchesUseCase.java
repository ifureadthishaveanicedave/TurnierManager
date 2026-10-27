package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.GenerateMatchesInputBoundary;
import de.dhbw.turniermanager.application.boundary.GenerateMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class GenerateMatchesUseCase implements GenerateMatchesInputBoundary {
    private final GenerateMatchesOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public GenerateMatchesUseCase(GenerateMatchesOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void generateMatches(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Das Turnier '" + tournamentName + "' existiert nicht.");
            return;
        }

        if (tournament.getRegisteredFighters().isEmpty()) {
            outputBoundary.presentError("Es sind keine Kämpfer im Turnier registriert.");
            return;
        }

        try {
            tournament.generateMatches();
            tournamentRepo.update(tournament);
            outputBoundary.presentMatches(tournament.getName(), tournament.getMatches());
        } catch (IllegalStateException e) {
            outputBoundary.presentError(e.getMessage());
        }
    }
}


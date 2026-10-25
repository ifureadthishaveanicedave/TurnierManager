package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.AdvanceRoundInputBoundary;
import de.dhbw.turniermanager.application.boundary.AdvanceRoundOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class AdvanceRoundUseCase implements AdvanceRoundInputBoundary {
    private final AdvanceRoundOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public AdvanceRoundUseCase(AdvanceRoundOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void advanceRound(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        try {
            tournament.advanceToNextRound();
            tournamentRepo.update(tournament);
            outputBoundary.presentNextRound(tournamentName, tournament.getMatches().size());
        } catch (IllegalStateException e) {
            outputBoundary.presentError(e.getMessage());
        }
    }
}
package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.RecordMatchResultInputBoundary;
import de.dhbw.turniermanager.application.boundary.RecordMatchResultOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

public class RecordMatchResultUseCase implements RecordMatchResultInputBoundary {
    private final RecordMatchResultOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public RecordMatchResultUseCase(RecordMatchResultOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void recordResult(String tournamentName, int matchNumber, String winnerName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        try {
            // matchNumber aus der UI ist 1-basiert, Index ist 0-basiert
            tournament.resolveMatch(matchNumber - 1, winnerName);
            tournamentRepo.update(tournament);
            outputBoundary.presentResultRecorded(matchNumber, winnerName);
        } catch (IllegalArgumentException e) {
            outputBoundary.presentError(e.getMessage());
        }
    }
}
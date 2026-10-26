package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.DeleteTournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.DeleteTournamentOutputBoundary;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;

public class DeleteTournamentUseCase implements DeleteTournamentInputBoundary {
    private final DeleteTournamentOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;

    public DeleteTournamentUseCase(DeleteTournamentOutputBoundary outputBoundary, TournamentRepository tournamentRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void deleteTournament(String tournamentName) {
        boolean exists = tournamentRepo.findAll().stream()
                .anyMatch(t -> t.getName().equals(tournamentName));

        if (!exists) {
            outputBoundary.presentError("Das Turnier '" + tournamentName + "' existiert nicht.");
            return;
        }

        tournamentRepo.delete(tournamentName);
        outputBoundary.presentSuccess(tournamentName);
    }
}
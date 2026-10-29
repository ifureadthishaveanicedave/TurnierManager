package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.DeleteTournamentInputBoundary;

public class DeleteTournamentController {
    private final DeleteTournamentInputBoundary inputBoundary;

    public DeleteTournamentController(DeleteTournamentInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleDeleteTournament(String tournamentName) {
        if (tournamentName == null || tournamentName.trim().isEmpty()) {
            System.out.println("Der Turniername darf nicht leer sein.");
            return;
        }
        inputBoundary.deleteTournament(tournamentName);
    }
}
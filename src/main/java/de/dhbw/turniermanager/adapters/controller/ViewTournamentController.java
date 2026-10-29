package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.ViewTournamentInputBoundary;

public class ViewTournamentController {
    private final ViewTournamentInputBoundary inputBoundary;

    public ViewTournamentController(ViewTournamentInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleViewTournament(String tournamentName) {
        if (tournamentName == null || tournamentName.trim().isEmpty()) {
            System.out.println("Der Turniername darf nicht leer sein.");
            return;
        }
        inputBoundary.viewTournament(tournamentName);
    }
}
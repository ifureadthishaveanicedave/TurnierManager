package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.TournamentInputBoundary;

public class TournamentController {
    private final TournamentInputBoundary inputBoundary;

    public TournamentController(TournamentInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleCreateTournament(String name, String weightClass) {
        if (name == null || name.trim().isEmpty() || weightClass == null || weightClass.trim().isEmpty()) {
            System.out.println("[Der Turniername, die Gewichtsklasse darf nicht leer sein.");
            return;
        }
        inputBoundary.createTournament(name, weightClass);
    }
}

package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.EnrollFighterInputBoundary;

public class EnrollFighterController {
    private final EnrollFighterInputBoundary inputBoundary;

    public EnrollFighterController(EnrollFighterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleEnrollFighter(String tournamentName, String fighterName) {
        if (tournamentName == null || tournamentName.trim().isEmpty() ||
                fighterName == null || fighterName.trim().isEmpty()) {
            System.out.println("[FEHLER] Eingaben dürfen nicht leer sein.");
            return;
        }
        inputBoundary.enrollFighter(tournamentName, fighterName);
    }
}
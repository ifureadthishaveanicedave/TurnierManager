package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.AdvanceRoundInputBoundary;

public class AdvanceRoundController {
    private final AdvanceRoundInputBoundary inputBoundary;

    public AdvanceRoundController(AdvanceRoundInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleAdvanceRound(String tournamentName) {
        inputBoundary.advanceRound(tournamentName);
    }
}
package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.ViewMatchesInputBoundary;

public class ViewMatchesController {
    private final ViewMatchesInputBoundary inputBoundary;

    public ViewMatchesController(ViewMatchesInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleViewMatches(String tournamentName) {
        inputBoundary.viewMatches(tournamentName);
    }
}
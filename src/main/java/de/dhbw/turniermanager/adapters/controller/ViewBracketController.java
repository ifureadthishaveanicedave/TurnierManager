package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.ViewBracketInputBoundary;

public class ViewBracketController {
    private final ViewBracketInputBoundary inputBoundary;

    public ViewBracketController(ViewBracketInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleViewBracket(String tournamentName) {
        inputBoundary.viewBracket(tournamentName);
    }
}
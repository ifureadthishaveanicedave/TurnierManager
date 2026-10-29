package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.ViewDojoRankingInputBoundary;

public class ViewDojoRankingController {
    private final ViewDojoRankingInputBoundary inputBoundary;

    public ViewDojoRankingController(ViewDojoRankingInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleViewGlobalRanking() {
        inputBoundary.viewGlobalRanking();
    }
}
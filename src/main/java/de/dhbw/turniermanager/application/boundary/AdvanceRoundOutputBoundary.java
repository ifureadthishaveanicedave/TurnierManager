package de.dhbw.turniermanager.application.boundary;

public interface AdvanceRoundOutputBoundary {
    void presentNextRound(String tournamentName, int newMatchCount);
    void presentError(String errorMessage);
}
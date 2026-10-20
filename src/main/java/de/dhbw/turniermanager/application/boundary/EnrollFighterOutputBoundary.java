package de.dhbw.turniermanager.application.boundary;

public interface EnrollFighterOutputBoundary {
    void presentSuccess(String fighterName, String tournamentName);
    void presentError(String errorMessage);
}
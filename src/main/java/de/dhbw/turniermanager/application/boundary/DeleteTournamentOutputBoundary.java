package de.dhbw.turniermanager.application.boundary;

public interface DeleteTournamentOutputBoundary {
    void presentSuccess(String tournamentName);
    void presentError(String errorMessage);
}
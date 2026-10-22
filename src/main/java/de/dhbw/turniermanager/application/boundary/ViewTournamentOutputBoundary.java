package de.dhbw.turniermanager.application.boundary;

import java.util.List;

public interface ViewTournamentOutputBoundary {
    void presentTournament(String tournamentName, List<String> registeredFighters);
    void presentError(String errorMessage);
}
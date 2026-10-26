package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.DeleteTournamentOutputBoundary;

public class ConsoleDeleteTournamentPresenter implements DeleteTournamentOutputBoundary {
    @Override
    public void presentSuccess(String tournamentName) {
        System.out.println("\n[ERFOLG] Das Turnier '" + tournamentName + "' wurde dauerhaft gelöscht.");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n[FEHLER] " + errorMessage);
    }
}
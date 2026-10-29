package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.EnrollFighterOutputBoundary;

public class ConsoleEnrollFighterPresenter implements EnrollFighterOutputBoundary {
    @Override
    public void presentSuccess(String fighterName, String tournamentName) {
        System.out.println("\n[ERFOLG] Kämpfer '" + fighterName + "' wurde dem Turnier '" + tournamentName + "' hinzugefügt.");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
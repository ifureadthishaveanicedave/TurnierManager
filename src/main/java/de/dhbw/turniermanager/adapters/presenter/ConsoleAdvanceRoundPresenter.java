package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.AdvanceRoundOutputBoundary;

public class ConsoleAdvanceRoundPresenter implements AdvanceRoundOutputBoundary {
    @Override
    public void presentNextRound(String tournamentName, int newMatchCount) {
        System.out.println("\n[ERFOLG] Nächste Runde für '" + tournamentName + "' gestartet!");
        System.out.println("Es wurden " + newMatchCount + " neue Kämpfe generiert.");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
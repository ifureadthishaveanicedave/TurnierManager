package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.RecordMatchResultOutputBoundary;

public class ConsoleRecordMatchResultPresenter implements RecordMatchResultOutputBoundary {
    @Override
    public void presentResultRecorded(int matchNumber, String winnerName) {
        System.out.println("\n[ERFOLG] Gewinner für Kampf " + matchNumber + " (" + winnerName + ") wurde gespeichert.");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n[FEHLER] " + errorMessage);
    }
}
package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.RecordMatchResultInputBoundary;

public class RecordMatchResultController {
    private final RecordMatchResultInputBoundary inputBoundary;

    public RecordMatchResultController(RecordMatchResultInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleRecordResult(String tournamentName, String matchNumStr, String winnerName) {
        try {
            int matchNumber = Integer.parseInt(matchNumStr);
            inputBoundary.recordResult(tournamentName, matchNumber, winnerName);
        } catch (NumberFormatException e) {
            System.out.println("[FEHLER] Die Kampf-Nummer muss eine Zahl sein.");
        }
    }
}
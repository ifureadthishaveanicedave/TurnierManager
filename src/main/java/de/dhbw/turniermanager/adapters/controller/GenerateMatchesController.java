package de.dhbw.turniermanager.adapters.controller;
import de.dhbw.turniermanager.application.boundary.GenerateMatchesInputBoundary;

public class GenerateMatchesController {
    private final GenerateMatchesInputBoundary inputBoundary;

    public GenerateMatchesController(GenerateMatchesInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleGenerateMatches(String tournamentName) {
        if (tournamentName == null || tournamentName.trim().isEmpty()) {
            System.out.println("Der Turniername darf nicht leer sein.");
            return;
        }
        inputBoundary.generateMatches(tournamentName);
    }
}
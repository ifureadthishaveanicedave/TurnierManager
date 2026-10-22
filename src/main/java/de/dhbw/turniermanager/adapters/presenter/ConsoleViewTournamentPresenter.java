package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewTournamentOutputBoundary;
import java.util.List;

public class ConsoleViewTournamentPresenter implements ViewTournamentOutputBoundary {
    @Override
    public void presentTournament(String tournamentName, List<String> fighters) {
        System.out.println("\n=== TURNIERDETAILS: " + tournamentName.toUpperCase() + " ===");
        if (fighters.isEmpty()) {
            System.out.println("Noch keine Kämpfer registriert.");
        } else {
            System.out.println("Registrierte Kämpfer (" + fighters.size() + "):");
            for (int i = 0; i < fighters.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + fighters.get(i));
            }
        }
        System.out.println("=====================================");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n[FEHLER] " + errorMessage);
    }
}
package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;

public class ConsoleViewMatchesPresenter implements ViewMatchesOutputBoundary {
    @Override
    public void presentMatches(String tournamentName, List<Match> matches) {
        System.out.println("\n=== AKTUELLE RUNDE: " + tournamentName.toUpperCase() + " ===");
        for (int i = 0; i < matches.size(); i++) {
            Match m = matches.get(i);
            String status = (m.getWinner() != null) ? " [BEENDET - Sieger: " + m.getWinner() + "]" : " [AUSSTEHEND]";
            System.out.println("Kampf " + (i + 1) + ": " + m.getFighter1() + " vs. " + m.getFighter2() + status);
        }
        System.out.println("=========================================");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n[FEHLER] " + errorMessage);
    }
}
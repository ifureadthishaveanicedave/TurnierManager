package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewBracketOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;

public class ConsoleViewBracketPresenter implements ViewBracketOutputBoundary {
    @Override
    public void presentBracket(String tournamentName, List<List<Match>> history, List<Match> currentRound) {
        System.out.println("\n=== TURNIERBAUM: " + tournamentName.toUpperCase() + " ===");

        int roundCounter = 1;
        for (List<Match> round : history) {
            System.out.println("--- Runde " + roundCounter + " (Abgeschlossen) ---");
            printMatches(round);
            roundCounter++;
        }

        if (!currentRound.isEmpty()) {
            System.out.println("--- Runde " + roundCounter + " (Aktuell) ---");
            printMatches(currentRound);
        } else if (history.isEmpty()) {
            System.out.println("Das Turnier hat noch nicht begonnen.");
        } else {
            System.out.println("--- Turnier beendet ---");
        }
        System.out.println("=================================");
    }

    private void printMatches(List<Match> matches) {
        for (Match m : matches) {
            String winnerStr = (m.getWinner() != null) ? " -> Sieger: " + m.getWinner() : "";
            System.out.println("  " + m.getFighter1() + " vs. " + m.getFighter2() + winnerStr);
        }
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n " + errorMessage);
    }
}
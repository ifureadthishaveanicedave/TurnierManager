package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewBracketOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;
import java.util.Map;

public class ConsoleViewBracketPresenter implements ViewBracketOutputBoundary {

    @Override
    public void presentBracket(String tournamentName, List<List<Match>> history, List<Match> currentRound, Map<String, String> fighterDojos) {
        System.out.println("\n=== TURNIERBAUM: " + tournamentName.toUpperCase() + " ===");

        int roundCounter = 1;
        for (List<Match> round : history) {
            System.out.println("--- Runde " + roundCounter + " (Abgeschlossen) ---");
            printMatches(round, fighterDojos);
            roundCounter++;
        }

        if (!currentRound.isEmpty()) {
            System.out.println("--- Runde " + roundCounter + " (Aktuell) ---");
            printMatches(currentRound, fighterDojos);
        } else if (history.isEmpty()) {
            System.out.println("Das Turnier hat noch nicht begonnen.");
        } else {
            System.out.println("--- Turnier beendet ---");
        }
        System.out.println("=================================");
    }

    private void printMatches(List<Match> matches, Map<String, String> fighterDojos) {
        for (Match m : matches) {
            String f1Display = formatName(m.getFighter1(), fighterDojos);
            String f2Display = formatName(m.getFighter2(), fighterDojos);
            String winnerDisplay = (m.getWinner() != null) ? formatName(m.getWinner(), fighterDojos) : null;

            String winnerStr = (winnerDisplay != null) ? " -> Sieger: " + winnerDisplay : "";
            System.out.println("  " + f1Display + " vs. " + f2Display + winnerStr);
        }
    }

    private String formatName(String name, Map<String, String> dojos) {
        if (name == null || name.equals("Freilos")) return "Freilos";
        String dojo = dojos.getOrDefault(name, "");
        return dojo.isEmpty() ? name : name + " (" + dojo + ")";
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
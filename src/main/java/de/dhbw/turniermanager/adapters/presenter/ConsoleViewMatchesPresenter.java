package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;
import java.util.Map;

public class ConsoleViewMatchesPresenter implements ViewMatchesOutputBoundary {

    @Override
    public void presentMatches(String tournamentName, List<Match> matches, Map<String, String> fighterDojos) {
        System.out.println("\n=== AKTUELLE RUNDE: " + tournamentName.toUpperCase() + " ===");
        for (int i = 0; i < matches.size(); i++) {
            Match m = matches.get(i);
            String f1Display = formatName(m.getFighter1(), fighterDojos);
            String f2Display = formatName(m.getFighter2(), fighterDojos);
            String status = (m.getWinner() != null) ? " [BEENDET - Sieger: " + m.getWinner() + "]" : " [AUSSTEHEND]";

            System.out.println("Kampf " + (i + 1) + ": " + f1Display + " vs. " + f2Display + status);
        }
        System.out.println("=========================================");
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
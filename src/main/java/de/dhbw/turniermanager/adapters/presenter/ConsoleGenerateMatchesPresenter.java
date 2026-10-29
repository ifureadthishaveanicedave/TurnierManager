package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.GenerateMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;

public class ConsoleGenerateMatchesPresenter implements GenerateMatchesOutputBoundary {
    @Override
    public void presentMatches(String tournamentName, List<Match> matches) {
        System.out.println("\n=== KAMPFPLAN: " + tournamentName.toUpperCase() + " ===");
        for (int i = 0; i < matches.size(); i++) {
            Match m = matches.get(i);
            System.out.println("Kampf " + (i + 1) + ": " + m.getFighter1() + " vs. " + m.getFighter2());
        }
        System.out.println("=================================");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.ViewDojoRankingOutputBoundary;
import java.util.List;
import java.util.Map;

public class ConsoleViewDojoRankingPresenter implements ViewDojoRankingOutputBoundary {
    @Override
    public void presentGlobalRanking(List<Map.Entry<String, Integer>> ranking) {
        System.out.println("\n=== GLOBALE DOJO-RANGLISTE (ALLE TURNIERE) ===");
        if (ranking.isEmpty()) {
            System.out.println("Noch keine Siege in einem Turnier erfasst.");
        } else {
            int platz = 1;
            for (Map.Entry<String, Integer> entry : ranking) {
                System.out.println(platz + ". Platz: " + entry.getKey() + " (" + entry.getValue() + " Siege)");
                platz++;
            }
        }
        System.out.println("==============================================");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
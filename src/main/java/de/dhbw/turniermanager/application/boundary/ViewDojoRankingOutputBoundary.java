package de.dhbw.turniermanager.application.boundary;
import java.util.List;
import java.util.Map;

public interface ViewDojoRankingOutputBoundary {
    void presentGlobalRanking(List<Map.Entry<String, Integer>> ranking);
    void presentError(String errorMessage);
}
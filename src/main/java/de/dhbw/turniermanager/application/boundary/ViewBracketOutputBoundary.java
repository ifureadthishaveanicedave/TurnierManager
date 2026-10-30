package de.dhbw.turniermanager.application.boundary;

import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;
import java.util.Map;

public interface ViewBracketOutputBoundary {
    void presentBracket(String tournamentName, List<List<Match>> history, List<Match> currentRound, Map<String, String> fighterDojos);
    void presentError(String errorMessage);
}
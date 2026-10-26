package de.dhbw.turniermanager.application.boundary;

import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;

public interface ViewBracketOutputBoundary {
    void presentBracket(String tournamentName, List<List<Match>> history, List<Match> currentRound);
    void presentError(String errorMessage);
}
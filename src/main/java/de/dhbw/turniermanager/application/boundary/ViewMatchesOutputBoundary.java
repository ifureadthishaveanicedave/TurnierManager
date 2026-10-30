package de.dhbw.turniermanager.application.boundary;

import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;
import java.util.Map;

public interface ViewMatchesOutputBoundary {
    void presentMatches(String tournamentName, List<Match> matches, Map<String, String> fighterDojos);
    void presentError(String errorMessage);
}
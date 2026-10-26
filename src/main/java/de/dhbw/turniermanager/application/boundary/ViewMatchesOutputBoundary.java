package de.dhbw.turniermanager.application.boundary;

import de.dhbw.turniermanager.domain.match.Match;
import java.util.List;

public interface ViewMatchesOutputBoundary {
    void presentMatches(String tournamentName, List<Match> matches);
    void presentError(String errorMessage);
}
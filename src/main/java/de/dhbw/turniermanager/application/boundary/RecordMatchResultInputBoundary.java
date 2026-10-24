package de.dhbw.turniermanager.application.boundary;

public interface RecordMatchResultInputBoundary {
    void recordResult(String tournamentName, int matchNumber, String winnerName);
}
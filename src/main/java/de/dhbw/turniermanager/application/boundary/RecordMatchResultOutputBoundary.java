package de.dhbw.turniermanager.application.boundary;

public interface RecordMatchResultOutputBoundary {
    void presentResultRecorded(int matchNumber, String winnerName);
    void presentError(String errorMessage);
}
package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;

public class ConsoleTournamentPresenter implements TournamentOutputBoundary {
    @Override
    public void presentTournamentCreated(String tournamentName) {
        System.out.println("\n[ERFOLG] Das Turnier '" + tournamentName + "' wurde erfolgreich erstellt!");
    }
}

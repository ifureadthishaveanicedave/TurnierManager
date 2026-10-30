package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;

public class ConsoleTournamentPresenter implements TournamentOutputBoundary {

    @Override
    public void presentTournamentCreated(String name) {
        System.out.println("\n Das Turnier '" + name + "' wurde erfolgreich erstellt!");
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
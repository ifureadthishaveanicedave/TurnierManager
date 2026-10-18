package de.dhbw.turniermanager.adapters.presenter;

import de.dhbw.turniermanager.application.boundary.FighterOutputBoundary;

public class ConsoleFighterPresenter implements FighterOutputBoundary {
    @Override
    public void presentFighterAdded(String name, String dojo) {
        System.out.println("\n[ERFOLG] Kämpfer '" + name + "' vom Team '" + dojo + "' wurde registriert!");
    }
}
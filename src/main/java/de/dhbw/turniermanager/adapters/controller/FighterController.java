package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.boundary.FighterInputBoundary;

public class FighterController {
    private final FighterInputBoundary inputBoundary;

    public FighterController(FighterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleAddFighter(String name, String dojo) {
        if (name == null || name.trim().isEmpty() || dojo == null || dojo.trim().isEmpty()) {
            System.out.println("Name und Team dürfen nicht leer sein.");
            return;
        }
        inputBoundary.addFighter(name, dojo);
    }
}
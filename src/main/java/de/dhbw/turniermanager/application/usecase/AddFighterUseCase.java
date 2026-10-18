package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.FighterInputBoundary;
import de.dhbw.turniermanager.application.boundary.FighterOutputBoundary;
import de.dhbw.turniermanager.domain.fighter.Fighter;

public class AddFighterUseCase implements FighterInputBoundary {
    private final FighterOutputBoundary outputBoundary;

    public AddFighterUseCase(FighterOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addFighter(String name, String dojo) {
        Fighter fighter = new Fighter(name, dojo);
        // Später: fighterRepository.save(fighter);
        outputBoundary.presentFighterAdded(fighter.getName(), fighter.getDojo());
    }
}
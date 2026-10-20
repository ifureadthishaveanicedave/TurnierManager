package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.FighterInputBoundary;
import de.dhbw.turniermanager.application.boundary.FighterOutputBoundary;
import de.dhbw.turniermanager.domain.fighter.Fighter;
import de.dhbw.turniermanager.domain.repository.FighterRepository;

public class AddFighterUseCase implements FighterInputBoundary {
    private final FighterOutputBoundary outputBoundary;
    private final FighterRepository repository;

    public AddFighterUseCase(FighterOutputBoundary outputBoundary, FighterRepository repository) {
        this.outputBoundary = outputBoundary;
        this.repository = repository;
    }

    @Override
    public void addFighter(String name, String dojo) {
        Fighter fighter = new Fighter(name, dojo);
        repository.save(fighter); // Echte Persistenz
        outputBoundary.presentFighterAdded(fighter.getName(), fighter.getDojo());
    }
}
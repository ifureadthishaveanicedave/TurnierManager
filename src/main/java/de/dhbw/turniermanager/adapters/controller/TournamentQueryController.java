package de.dhbw.turniermanager.adapters.controller;

import de.dhbw.turniermanager.application.usecase.TournamentQueryUseCase;

public class TournamentQueryController {
    private final TournamentQueryUseCase queryUseCase;

    public TournamentQueryController(TournamentQueryUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    public boolean checkTournamentExists(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return queryUseCase.tournamentExists(name);
    }
}
package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.*;
import de.dhbw.turniermanager.adapters.presenter.*;
import de.dhbw.turniermanager.application.boundary.*;
import de.dhbw.turniermanager.application.usecase.*;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.infrastructure.persistence.JsonFighterRepository;
import de.dhbw.turniermanager.infrastructure.persistence.JsonTournamentRepository;

public class Main {
    public static void main(String[] args) {
        // 1. Repositories
        TournamentRepository tournamentRepo = new JsonTournamentRepository("tournaments.json");
        FighterRepository fighterRepo = new JsonFighterRepository("fighters.json");

        // 2. Presenter
        TournamentOutputBoundary tournamentPresenter = new ConsoleTournamentPresenter();
        FighterOutputBoundary fighterPresenter = new ConsoleFighterPresenter();
        EnrollFighterOutputBoundary enrollPresenter = new ConsoleEnrollFighterPresenter();
        ViewTournamentOutputBoundary viewPresenter = new ConsoleViewTournamentPresenter();
        GenerateMatchesOutputBoundary matchPresenter = new ConsoleGenerateMatchesPresenter();

        // 3. Use-Cases
        TournamentInputBoundary tournamentUseCase = new CreateTournamentUseCase(tournamentPresenter, tournamentRepo);
        FighterInputBoundary fighterUseCase = new AddFighterUseCase(fighterPresenter, fighterRepo);
        EnrollFighterInputBoundary enrollUseCase = new EnrollFighterUseCase(enrollPresenter, tournamentRepo, fighterRepo);
        ViewTournamentInputBoundary viewUseCase = new ViewTournamentUseCase(viewPresenter, tournamentRepo);
        GenerateMatchesInputBoundary matchUseCase = new GenerateMatchesUseCase(matchPresenter, tournamentRepo);

        // 4. Controller
        TournamentController tournamentController = new TournamentController(tournamentUseCase);
        FighterController fighterController = new FighterController(fighterUseCase);
        EnrollFighterController enrollController = new EnrollFighterController(enrollUseCase);
        ViewTournamentController viewController = new ViewTournamentController(viewUseCase);
        GenerateMatchesController matchController = new GenerateMatchesController(matchUseCase);

        // 5. Router
        ConsoleRouter router = new ConsoleRouter(tournamentController, fighterController, enrollController, viewController, matchController);
        router.start();
    }
}

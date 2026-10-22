package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.EnrollFighterController;
import de.dhbw.turniermanager.adapters.controller.FighterController;
import de.dhbw.turniermanager.adapters.controller.TournamentController;
import de.dhbw.turniermanager.adapters.controller.ViewTournamentController;
import de.dhbw.turniermanager.adapters.presenter.ConsoleEnrollFighterPresenter;
import de.dhbw.turniermanager.adapters.presenter.ConsoleFighterPresenter;
import de.dhbw.turniermanager.adapters.presenter.ConsoleTournamentPresenter;
import de.dhbw.turniermanager.adapters.presenter.ConsoleViewTournamentPresenter;
import de.dhbw.turniermanager.application.boundary.*;
import de.dhbw.turniermanager.application.usecase.AddFighterUseCase;
import de.dhbw.turniermanager.application.usecase.CreateTournamentUseCase;
import de.dhbw.turniermanager.application.usecase.EnrollFighterUseCase;
import de.dhbw.turniermanager.application.usecase.ViewTournamentUseCase;
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

        // 3. Use-Cases
        TournamentInputBoundary tournamentUseCase = new CreateTournamentUseCase(tournamentPresenter, tournamentRepo);
        FighterInputBoundary fighterUseCase = new AddFighterUseCase(fighterPresenter, fighterRepo);
        EnrollFighterInputBoundary enrollUseCase = new EnrollFighterUseCase(enrollPresenter, tournamentRepo, fighterRepo);
        ViewTournamentInputBoundary viewUseCase = new ViewTournamentUseCase(viewPresenter, tournamentRepo);

        // 4. Controller
        TournamentController tournamentController = new TournamentController(tournamentUseCase);
        FighterController fighterController = new FighterController(fighterUseCase);
        EnrollFighterController enrollController = new EnrollFighterController(enrollUseCase);
        ViewTournamentController viewController = new ViewTournamentController(viewUseCase);

        // 5. Router
        ConsoleRouter router = new ConsoleRouter(tournamentController, fighterController, enrollController, viewController);
        router.start();
    }
}

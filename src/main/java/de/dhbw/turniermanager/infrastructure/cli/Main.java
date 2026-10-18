package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.FighterController;
import de.dhbw.turniermanager.adapters.controller.TournamentController;
import de.dhbw.turniermanager.adapters.presenter.ConsoleFighterPresenter;
import de.dhbw.turniermanager.adapters.presenter.ConsoleTournamentPresenter;
import de.dhbw.turniermanager.application.boundary.FighterInputBoundary;
import de.dhbw.turniermanager.application.boundary.FighterOutputBoundary;
import de.dhbw.turniermanager.application.boundary.TournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;
import de.dhbw.turniermanager.application.usecase.AddFighterUseCase;
import de.dhbw.turniermanager.application.usecase.CreateTournamentUseCase;
import de.dhbw.turniermanager.infrastructure.persistence.JsonTournamentRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;

public class Main {
    public static void main(String[] args) {
        TournamentRepository tournamentRepo = new JsonTournamentRepository("tournaments.json");

        TournamentOutputBoundary presenter = new ConsoleTournamentPresenter();
        TournamentInputBoundary useCase = new CreateTournamentUseCase(presenter, tournamentRepo);
        TournamentController controller = new TournamentController(useCase);

        FighterOutputBoundary fighterPresenter = new ConsoleFighterPresenter();
        FighterInputBoundary fighterUseCase = new AddFighterUseCase(fighterPresenter);
        FighterController fighterController = new FighterController(fighterUseCase);

        ConsoleRouter router = new ConsoleRouter(controller, fighterController);
        router.start();
    }
}

package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.TournamentController;
import de.dhbw.turniermanager.adapters.presenter.ConsoleTournamentPresenter;
import de.dhbw.turniermanager.application.boundary.TournamentInputBoundary;
import de.dhbw.turniermanager.application.boundary.TournamentOutputBoundary;
import de.dhbw.turniermanager.application.usecase.CreateTournamentUseCase;

public class Main {
    public static void main(String[] args) {
        TournamentOutputBoundary presenter = new ConsoleTournamentPresenter();

        TournamentInputBoundary useCase = new CreateTournamentUseCase(presenter);

        TournamentController controller = new TournamentController(useCase);

        ConsoleRouter router = new ConsoleRouter(controller);
        router.start();
    }
}

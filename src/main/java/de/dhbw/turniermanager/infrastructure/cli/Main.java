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
        RecordMatchResultOutputBoundary recordPresenter = new ConsoleRecordMatchResultPresenter();
        AdvanceRoundOutputBoundary advancePresenter = new ConsoleAdvanceRoundPresenter();
        ViewMatchesOutputBoundary viewMatchesPresenter = new ConsoleViewMatchesPresenter();
        ViewBracketOutputBoundary bracketPresenter = new ConsoleViewBracketPresenter();

        // 3. Use-Cases
        TournamentInputBoundary tournamentUseCase = new CreateTournamentUseCase(tournamentPresenter, tournamentRepo);
        FighterInputBoundary fighterUseCase = new AddFighterUseCase(fighterPresenter, fighterRepo);
        EnrollFighterInputBoundary enrollUseCase = new EnrollFighterUseCase(enrollPresenter, tournamentRepo, fighterRepo);
        ViewTournamentInputBoundary viewUseCase = new ViewTournamentUseCase(viewPresenter, tournamentRepo);
        GenerateMatchesInputBoundary matchUseCase = new GenerateMatchesUseCase(matchPresenter, tournamentRepo);
        RecordMatchResultInputBoundary recordUseCase = new RecordMatchResultUseCase(recordPresenter, tournamentRepo);
        AdvanceRoundInputBoundary advanceUseCase = new AdvanceRoundUseCase(advancePresenter, tournamentRepo);
        ViewMatchesInputBoundary viewMatchesUseCase = new ViewMatchesUseCase(viewMatchesPresenter, tournamentRepo);
        ViewBracketInputBoundary bracketUseCase = new ViewBracketUseCase(bracketPresenter, tournamentRepo);

        // 4. Controller
        TournamentController tournamentController = new TournamentController(tournamentUseCase);
        FighterController fighterController = new FighterController(fighterUseCase);
        EnrollFighterController enrollController = new EnrollFighterController(enrollUseCase);
        ViewTournamentController viewController = new ViewTournamentController(viewUseCase);
        GenerateMatchesController matchController = new GenerateMatchesController(matchUseCase);
        RecordMatchResultController recordController = new RecordMatchResultController(recordUseCase);
        AdvanceRoundController advanceController = new AdvanceRoundController(advanceUseCase);
        ViewMatchesController viewMatchesController = new ViewMatchesController(viewMatchesUseCase);
        ViewBracketController bracketController = new ViewBracketController(bracketUseCase);

        // 5. Router
        TournamentExecutionRouter executionRouter = new TournamentExecutionRouter(
                recordController, advanceController, viewMatchesController, bracketController
        );
        ConsoleRouter router = new ConsoleRouter(
                tournamentController, fighterController, enrollController,
                viewController, matchController, executionRouter
        );
        router.start();
    }
}

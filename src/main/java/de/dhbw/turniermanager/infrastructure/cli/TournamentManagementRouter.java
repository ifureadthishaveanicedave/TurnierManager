package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.EnrollFighterController;
import de.dhbw.turniermanager.adapters.controller.ViewTournamentController;
import java.util.Scanner;

public class TournamentManagementRouter {
    private final EnrollFighterController enrollFighterController;
    private final ViewTournamentController viewTournamentController;
    private final TournamentExecutionRouter executionRouter;
    private final Scanner scanner;

    public TournamentManagementRouter(EnrollFighterController enrollFighterController,
                                      ViewTournamentController viewTournamentController,
                                      TournamentExecutionRouter executionRouter) {
        this.enrollFighterController = enrollFighterController;
        this.viewTournamentController = viewTournamentController;
        this.executionRouter = executionRouter;
        this.scanner = new Scanner(System.in);
    }

    public void startManagementMode(String tournamentName) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== TURNIER-VERWALTUNG: " + tournamentName.toUpperCase() + " ===");
            System.out.println("[1] Kämpfer zu diesem Turnier hinzufügen");
            System.out.println("[2] Turnierdetails anzeigen");
            System.out.println("[3] Turnier in den Live-Modus starten");
            System.out.println("[4] Zurück zum Hauptmenü");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Name des Kämpfers: ");
                    String fighterName = scanner.nextLine();
                    enrollFighterController.handleEnrollFighter(tournamentName, fighterName);
                    break;
                case "2":
                    viewTournamentController.handleViewTournament(tournamentName);
                    break;
                case "3":
                    executionRouter.startExecutionMode(tournamentName);
                    break;
                case "4":
                    running = false;
                    System.out.println("Verwaltung beendet...");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
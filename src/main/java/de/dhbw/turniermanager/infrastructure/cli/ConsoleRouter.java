package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.*;

import java.util.Scanner;

public class ConsoleRouter {
    private final TournamentController tournamentController;
    private final Scanner scanner;
    private final FighterController fighterController;
    private final EnrollFighterController enrollFighterController;
    private final ViewTournamentController viewTournamentController;
    private final TournamentExecutionRouter tournamentExecutionRouter;
    private final DeleteTournamentController deleteTournamentController;

    public ConsoleRouter(TournamentController tournamentController,
                         FighterController fighterController,
                         EnrollFighterController enrollFighterController,
                         ViewTournamentController viewTournamentController,
                         TournamentExecutionRouter tournamentExecutionRouter,
                         DeleteTournamentController deleteTournamentController) {
        this.tournamentController = tournamentController;
        this.fighterController = fighterController;
        this.enrollFighterController = enrollFighterController;
        this.viewTournamentController = viewTournamentController;
        this.tournamentExecutionRouter = tournamentExecutionRouter;
        this.deleteTournamentController = deleteTournamentController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Kampfsport-Turnier-Manager ===");
            System.out.println("[1] Neues Turnier erstellen");
            System.out.println("[2] Neuen Kämpfer registrieren");
            System.out.println("[3] Kämpfer zu Turnier hinzufügen");
            System.out.println("[4] Turnierdetails anzeigen");
            System.out.println("[5] Turnier öffnen");
            System.out.println("[6] Turnier löschen");
            System.out.println("[7] Beenden");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Turniername eingeben: ");
                    String name = scanner.nextLine();
                    tournamentController.handleCreateTournament(name);
                    break;
                case "2":
                    System.out.print("Name des Kämpfers: ");
                    String fighterName = scanner.nextLine();
                    System.out.print("Name des Teams/Dojos: ");
                    String dojo = scanner.nextLine();
                    fighterController.handleAddFighter(fighterName, dojo);
                    break;
                case "3":
                    System.out.print("Name des Turniers: ");
                    String tName = scanner.nextLine();
                    System.out.print("Name des Kämpfers: ");
                    String fName = scanner.nextLine();
                    enrollFighterController.handleEnrollFighter(tName, fName);
                    break;
                case "4":
                    System.out.print("Name des Turniers: ");
                    String viewName = scanner.nextLine();
                    viewTournamentController.handleViewTournament(viewName);
                    break;
                case "5":
                    System.out.print("Name des Turniers: ");
                    String openTournamentName = scanner.nextLine();
                    tournamentExecutionRouter.startExecutionMode(openTournamentName);
                    break;
                case "6":
                    System.out.print("Name des zu löschenden Turniers: ");
                    String deleteName = scanner.nextLine();
                    deleteTournamentController.handleDeleteTournament(deleteName);
                    break;
                case "7":
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}

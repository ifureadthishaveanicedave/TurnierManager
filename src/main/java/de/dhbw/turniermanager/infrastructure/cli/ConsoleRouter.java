package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.EnrollFighterController;
import de.dhbw.turniermanager.adapters.controller.FighterController;
import de.dhbw.turniermanager.adapters.controller.TournamentController;
import de.dhbw.turniermanager.adapters.controller.ViewTournamentController;

import java.util.Scanner;

public class ConsoleRouter {
    private final TournamentController tournamentController;
    private final Scanner scanner;
    private final FighterController fighterController;
    private final EnrollFighterController enrollFighterController;
    private final ViewTournamentController viewTournamentController;

    public ConsoleRouter(TournamentController tournamentController,
                         FighterController fighterController,
                         EnrollFighterController enrollFighterController,
                         ViewTournamentController viewTournamentController) {
        this.tournamentController = tournamentController;
        this.fighterController = fighterController;
        this.enrollFighterController = enrollFighterController;
        this.scanner = new Scanner(System.in);
        this.viewTournamentController = viewTournamentController;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Kampfsport-Turnier-Manager ===");
            System.out.println("[1] Neues Turnier erstellen");
            System.out.println("[2] Neuen Kämpfer registrieren");
            System.out.println("[3] Kämpfer zu Turnier hinzufügen");
            System.out.println("[4] Turnierdetails anzeigen");
            System.out.println("[5] Beenden");
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
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}

package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.FighterController;
import de.dhbw.turniermanager.adapters.controller.TournamentController;
import java.util.Scanner;

public class ConsoleRouter {
    private final TournamentController tournamentController;
    private final Scanner scanner;
    private final FighterController fighterController;

    public ConsoleRouter(TournamentController tournamentController, FighterController fighterController) {
        this.tournamentController = tournamentController;
        this.fighterController = fighterController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Kampfsport-Turnier-Manager ===");
            System.out.println("[1] Neues Turnier erstellen");
            System.out.println("[2] Neuen Kämpfer registrieren");
            System.out.println("[3] Beenden");
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
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}

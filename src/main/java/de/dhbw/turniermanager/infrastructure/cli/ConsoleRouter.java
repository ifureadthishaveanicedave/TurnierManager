package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.TournamentController;
import java.util.Scanner;

public class ConsoleRouter {
    private final TournamentController tournamentController;
    private final Scanner scanner;

    public ConsoleRouter(TournamentController tournamentController) {
        this.tournamentController = tournamentController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Kampfsport-Turnier-Manager ===");
            System.out.println("[1] Neues Turnier erstellen");
            System.out.println("[2] Beenden");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Turniername eingeben: ");
                    String name = scanner.nextLine();
                    tournamentController.handleCreateTournament(name);
                    break;
                case "2":
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}

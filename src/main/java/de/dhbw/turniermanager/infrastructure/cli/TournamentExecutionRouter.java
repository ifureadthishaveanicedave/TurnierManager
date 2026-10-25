package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.AdvanceRoundController;
import de.dhbw.turniermanager.adapters.controller.RecordMatchResultController;
import java.util.Scanner;

public class TournamentExecutionRouter {
    private final RecordMatchResultController recordMatchResultController;
    private final AdvanceRoundController advanceRoundController;
    private final Scanner scanner;

    public TournamentExecutionRouter(RecordMatchResultController recordMatchResultController,
                                     AdvanceRoundController advanceRoundController) {
        this.recordMatchResultController = recordMatchResultController;
        this.advanceRoundController = advanceRoundController;
        this.scanner = new Scanner(System.in);
    }

    public void startExecutionMode(String tournamentName) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== TURNIER-MODUS: " + tournamentName.toUpperCase() + " ===");
            System.out.println("[1] Ergebnis eintragen");
            System.out.println("[2] Nächste Runde generieren");
            System.out.println("[3] Zurück zum Hauptmenü");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Kampf-Nummer (z.B. 1): ");
                    String matchNum = scanner.nextLine();
                    System.out.print("Name des Gewinners: ");
                    String winnerName = scanner.nextLine();
                    recordMatchResultController.handleRecordResult(tournamentName, matchNum, winnerName);
                    break;
                case "2":
                    advanceRoundController.handleAdvanceRound(tournamentName);
                    break;
                case "3":
                    running = false;
                    System.out.println("Turnier-Modus wird beendet...");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
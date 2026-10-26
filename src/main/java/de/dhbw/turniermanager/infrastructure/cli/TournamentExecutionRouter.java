package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.AdvanceRoundController;
import de.dhbw.turniermanager.adapters.controller.RecordMatchResultController;
import de.dhbw.turniermanager.adapters.controller.ViewBracketController;
import de.dhbw.turniermanager.adapters.controller.ViewMatchesController;

import java.util.Scanner;

public class TournamentExecutionRouter {
    private final RecordMatchResultController recordMatchResultController;
    private final AdvanceRoundController advanceRoundController;
    private final ViewMatchesController viewMatchesController;
    private final ViewBracketController viewBracketController;
    private final Scanner scanner;

    public TournamentExecutionRouter(RecordMatchResultController recordMatchResultController,
                                     AdvanceRoundController advanceRoundController,
                                     ViewMatchesController viewMatchesController,
                                     ViewBracketController viewBracketController){
        this.recordMatchResultController = recordMatchResultController;
        this.advanceRoundController = advanceRoundController;
        this.viewMatchesController = viewMatchesController;
        this.viewBracketController = viewBracketController;
        this.scanner = new Scanner(System.in);
    }

    public void startExecutionMode(String tournamentName) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== TURNIER-MODUS: " + tournamentName.toUpperCase() + " ===");
            System.out.println("[1] Aktuelle Kämpfe anzeigen");
            System.out.println("[2] Ergebnis eintragen");
            System.out.println("[3] Nächste Runde generieren");
            System.out.println("[4] Gesamten Turnierbaum anzeigen");
            System.out.println("[5] Zurück zum Hauptmenü");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    viewMatchesController.handleViewMatches(tournamentName);
                    break;
                case "2":
                    System.out.print("Kampf-Nummer (z.B. 1): ");
                    String matchNum = scanner.nextLine();
                    System.out.print("Name des Gewinners: ");
                    String winnerName = scanner.nextLine();
                    recordMatchResultController.handleRecordResult(tournamentName, matchNum, winnerName);
                    break;
                case "3":
                    advanceRoundController.handleAdvanceRound(tournamentName);
                    break;
                case "4":
                    viewBracketController.handleViewBracket(tournamentName);
                    break;
                case "5":
                    running = false;
                    System.out.println("Turnier-Modus wird beendet...");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
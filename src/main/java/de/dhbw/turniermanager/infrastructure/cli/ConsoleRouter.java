package de.dhbw.turniermanager.infrastructure.cli;

import de.dhbw.turniermanager.adapters.controller.*;
import java.util.Scanner;

public class ConsoleRouter {
    private final TournamentController tournamentController;
    private final FighterController fighterController;
    private final DeleteTournamentController deleteTournamentController;
    private final TournamentQueryController tournamentQueryController;
    private final TournamentManagementRouter tournamentManagementRouter;
    private final ViewDojoRankingController viewDojoRankingController;
    private final Scanner scanner;

    public ConsoleRouter(TournamentController tournamentController,
                         FighterController fighterController,
                         DeleteTournamentController deleteTournamentController,
                         TournamentQueryController tournamentQueryController,
                         TournamentManagementRouter tournamentManagementRouter,
                         ViewDojoRankingController viewDojoRankingController) {
        this.tournamentController = tournamentController;
        this.fighterController = fighterController;
        this.deleteTournamentController = deleteTournamentController;
        this.tournamentQueryController = tournamentQueryController;
        this.tournamentManagementRouter = tournamentManagementRouter;
        this.viewDojoRankingController = viewDojoRankingController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Kampfsport-Turnier-Manager ===");
            System.out.println("[1] Neues Turnier erstellen");
            System.out.println("[2] Neuen Kämpfer im System registrieren");
            System.out.println("[3] Turnier verwalten (Kämpfer hinzufügen, Details, Starten)");
            System.out.println("[4] Turnier löschen");
            System.out.println("[5] Globale Dojo-Rangliste anzeigen");
            System.out.println("[6] Beenden");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Turniername eingeben: ");
                    String name = scanner.nextLine();
                    System.out.print("Gewichtsklasse (Enter für 'Offen'): ");
                    String tWeight = scanner.nextLine();
                    tournamentController.handleCreateTournament(name, tWeight);
                    break;
                case "2":
                    System.out.print("Name des Kämpfers: ");
                    String fighterName = scanner.nextLine();
                    System.out.print("Name des Teams/Dojos: ");
                    String dojo = scanner.nextLine();
                    System.out.print("Gewichtsklasse(Feder, Mittel, Schwer, Offen)");
                    String fWeight = scanner.nextLine();
                    fighterController.handleAddFighter(fighterName, dojo, fWeight);
                    break;
                case "3":
                    System.out.print("Name des Turniers: ");
                    String manageName = scanner.nextLine();
                    if (tournamentQueryController.checkTournamentExists(manageName)) {
                        tournamentManagementRouter.startManagementMode(manageName);
                    } else {
                        System.out.println("Das Turnier '" + manageName + "' existiert nicht.");
                    }
                    break;
                case "4":
                    System.out.print("Name des zu löschenden Turniers: ");
                    String deleteName = scanner.nextLine();
                    deleteTournamentController.handleDeleteTournament(deleteName);
                    break;
                case "5":
                    viewDojoRankingController.handleViewGlobalRanking();
                    break;
                case "6":
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewDojoRankingInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewDojoRankingOutputBoundary;
import de.dhbw.turniermanager.domain.match.Match;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;
import de.dhbw.turniermanager.domain.fighter.Fighter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewDojoRankingUseCase implements ViewDojoRankingInputBoundary {
    private final ViewDojoRankingOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;
    private final FighterRepository fighterRepo;

    public ViewDojoRankingUseCase(ViewDojoRankingOutputBoundary outputBoundary,
                                  TournamentRepository tournamentRepo,
                                  FighterRepository fighterRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
        this.fighterRepo = fighterRepo;
    }

    @Override
    public void viewGlobalRanking() {
        List<Tournament> allTournaments = tournamentRepo.findAll();
        List<Fighter> allFighters = fighterRepo.findAll();
        Map<String, Integer> dojoWins = new HashMap<>();

        for (Tournament tournament : allTournaments) {
            for (Match match : tournament.getAllMatches()) {
                if (match.getWinner() != null && !match.getWinner().equals("Freilos")) {
                    String winnerName = match.getWinner();
                    String dojo = allFighters.stream()
                            .filter(f -> f.getName().equals(winnerName))
                            .map(Fighter::getDojo)
                            .findFirst()
                            .orElse("Unbekanntes Dojo");

                    dojoWins.put(dojo, dojoWins.getOrDefault(dojo, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedRanking = dojoWins.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        outputBoundary.presentGlobalRanking(sortedRanking);
    }
}
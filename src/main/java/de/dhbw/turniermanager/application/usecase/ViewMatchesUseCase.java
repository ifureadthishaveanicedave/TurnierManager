package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewMatchesInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewMatchesOutputBoundary;
import de.dhbw.turniermanager.domain.fighter.Fighter;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

import java.util.Map;
import java.util.stream.Collectors;

public class ViewMatchesUseCase implements ViewMatchesInputBoundary {
    private final ViewMatchesOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;
    private final FighterRepository fighterRepo;

    public ViewMatchesUseCase(ViewMatchesOutputBoundary outputBoundary, TournamentRepository tournamentRepo, FighterRepository fighterRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
        this.fighterRepo = fighterRepo;
    }

    @Override
    public void viewMatches(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        if (tournament.getMatches().isEmpty()) {
            outputBoundary.presentError("Es gibt aktuell keine aktiven Kämpfe in diesem Turnier.");
            return;
        }

        Map<String, String> fighterDojos = fighterRepo.findAll().stream()
                .collect(Collectors.toMap(Fighter::getName, Fighter::getDojo, (d1, d2) -> d1));

        outputBoundary.presentMatches(tournament.getName(), tournament.getMatches(), fighterDojos);
    }
}

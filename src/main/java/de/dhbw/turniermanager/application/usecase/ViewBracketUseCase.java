package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.ViewBracketInputBoundary;
import de.dhbw.turniermanager.application.boundary.ViewBracketOutputBoundary;
import de.dhbw.turniermanager.domain.fighter.Fighter;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

import java.util.Map;
import java.util.stream.Collectors;

public class ViewBracketUseCase implements ViewBracketInputBoundary {
    private final ViewBracketOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;
    private final FighterRepository fighterRepo;

    public ViewBracketUseCase(ViewBracketOutputBoundary outputBoundary, TournamentRepository tournamentRepo, FighterRepository fighterRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
        this.fighterRepo = fighterRepo;
    }

    @Override
    public void viewBracket(String tournamentName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName))
                .findFirst()
                .orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier nicht gefunden.");
            return;
        }

        Map<String, String> fighterDojos = fighterRepo.findAll().stream()
                .collect(Collectors.toMap(Fighter::getName, Fighter::getDojo, (d1, d2) -> d1));

        outputBoundary.presentBracket(tournament.getName(), tournament.getPreviousRounds(), tournament.getMatches(), fighterDojos);
    }
}
package de.dhbw.turniermanager.application.usecase;

import de.dhbw.turniermanager.application.boundary.EnrollFighterInputBoundary;
import de.dhbw.turniermanager.application.boundary.EnrollFighterOutputBoundary;
import de.dhbw.turniermanager.domain.fighter.Fighter;
import de.dhbw.turniermanager.domain.repository.FighterRepository;
import de.dhbw.turniermanager.domain.repository.TournamentRepository;
import de.dhbw.turniermanager.domain.tournament.Tournament;

import java.util.List;

public class EnrollFighterUseCase implements EnrollFighterInputBoundary {
    private final EnrollFighterOutputBoundary outputBoundary;
    private final TournamentRepository tournamentRepo;
    private final FighterRepository fighterRepo;

    public EnrollFighterUseCase(EnrollFighterOutputBoundary outputBoundary,
                                TournamentRepository tournamentRepo,
                                FighterRepository fighterRepo) {
        this.outputBoundary = outputBoundary;
        this.tournamentRepo = tournamentRepo;
        this.fighterRepo = fighterRepo;
    }

    @Override
    public void enrollFighter(String tournamentName, String fighterName) {
        Tournament tournament = tournamentRepo.findAll().stream()
                .filter(t -> t.getName().equals(tournamentName)).findFirst().orElse(null);
        Fighter fighter = fighterRepo.findAll().stream()
                .filter(f -> f.getName().equals(fighterName)).findFirst().orElse(null);

        if (tournament == null) {
            outputBoundary.presentError("Turnier '" + tournamentName + "' existiert nicht.");
            return;
        }

        boolean fighterExists = fighterRepo.findAll().stream()
                .anyMatch(f -> f.getName().equals(fighterName));

        if (!fighterExists) {
            outputBoundary.presentError("Kämpfer '" + fighterName + "' existiert nicht.");
            return;
        }

        if (!tournament.getWeightClass().equalsIgnoreCase("Offen")
                && !tournament.getWeightClass().equalsIgnoreCase(fighter.getWeightClass())) {
            outputBoundary.presentError("Gewichtsklasse passt nicht! Turnier: " + tournament.getWeightClass() + ", Kämpfer: " + fighter.getWeightClass());
            return;
        }

        tournament.registerFighter(fighterName);

        // ersetzt komplette Datei
        List<Tournament> allTournaments = tournamentRepo.findAll();
        allTournaments.removeIf(t -> t.getName().equals(tournamentName));
        allTournaments.add(tournament);

        tournamentRepo.update(tournament);

        outputBoundary.presentSuccess(fighterName, tournamentName);
    }
}
package de.dhbw.turniermanager.domain.tournament;

import java.util.ArrayList;
import java.util.List;
import de.dhbw.turniermanager.domain.match.Match;

public class Tournament {
    private String name;
    private List<String> registeredFighters;
    private List<Match> matches;

    public Tournament() {
        this.registeredFighters = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public Tournament(String name) {
        this.name = name;
        this.registeredFighters = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //methode für ausgsbe später
    public List<String> getRegisteredFighters() {
        return registeredFighters;
    }

    public void registerFighter(String fighterName) {
        if (!this.registeredFighters.contains(fighterName)) {
            this.registeredFighters.add(fighterName);
        }
    }
    public List<Match> getMatches() {
        return matches;
    }

    public void generateMatches() {
        this.matches.clear();
        // Einfache sequenzielle Paarung (1vs2, 3vs4)
        for (int i = 0; i < registeredFighters.size(); i += 2) {
            String f1 = registeredFighters.get(i);
            // Bei ungerader Anzahl bekommt der letzte Kämpfer ein Freilos (Bye)
            String f2 = (i + 1 < registeredFighters.size()) ? registeredFighters.get(i + 1) : "Freilos";
            this.matches.add(new Match(f1, f2));
        }
    }
    public void resolveMatch(int matchIndex, String winnerName) {
        if (matchIndex < 0 || matchIndex >= this.matches.size()) {
            throw new IllegalArgumentException("Ungültige Kampf-Nummer.");
        }
        Match match = this.matches.get(matchIndex);
        if (!match.getFighter1().equals(winnerName) && !match.getFighter2().equals(winnerName)) {
            throw new IllegalArgumentException("Der Gewinner muss einer der beiden Kämpfer sein.");
        }
        match.setWinner(winnerName);
    }
    public void advanceToNextRound() {
        if (this.matches.isEmpty()) {
            throw new IllegalStateException("Es gibt noch keine aktiven Kämpfe.");
        }

        List<String> winners = new ArrayList<>();
        for (Match match : this.matches) {
            if (match.getWinner() == null) {
                throw new IllegalStateException("Es sind noch nicht alle Kämpfe dieser Runde beendet.");
            }
            winners.add(match.getWinner());
        }

        if (winners.size() == 1) {
            throw new IllegalStateException("Das Turnier ist beendet! Der Gesamtsieger ist: " + winners.get(0));
        }

        this.matches.clear();
        for (int i = 0; i < winners.size(); i += 2) {
            String f1 = winners.get(i);
            String f2 = (i + 1 < winners.size()) ? winners.get(i + 1) : "Freilos";
            this.matches.add(new Match(f1, f2));
        }
    }
}
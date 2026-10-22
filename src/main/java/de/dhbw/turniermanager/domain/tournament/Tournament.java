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
}
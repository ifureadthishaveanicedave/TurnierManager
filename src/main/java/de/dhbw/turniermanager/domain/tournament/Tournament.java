package de.dhbw.turniermanager.domain.tournament;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private String name;
    private List<String> registeredFighters;

    public Tournament() {
        // Zwingend für Jackson, damit die Liste beim Einlesen nicht null ist
        this.registeredFighters = new ArrayList<>();
    }

    public Tournament(String name) {
        this.name = name;
        this.registeredFighters = new ArrayList<>();
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
}
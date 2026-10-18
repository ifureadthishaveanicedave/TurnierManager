package de.dhbw.turniermanager.domain.tournament;

public class Tournament {
    private String name;

    // Konstruktor für JSON-Deserialisierung
    public Tournament() {}

    public Tournament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
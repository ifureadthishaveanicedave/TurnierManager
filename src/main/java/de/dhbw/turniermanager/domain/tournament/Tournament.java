package de.dhbw.turniermanager.domain.tournament;

public class Tournament {
    private final String name;

    public Tournament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

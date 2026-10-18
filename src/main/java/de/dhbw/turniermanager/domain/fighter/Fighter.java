package de.dhbw.turniermanager.domain.fighter;

public class Fighter {
    private final String name;
    private final String dojo;

    public Fighter(String name, String dojo) {
        this.name = name;
        this.dojo = dojo;
    }

    public String getName() {
        return name;
    }

    public String getDojo() {
        return dojo;
    }
}
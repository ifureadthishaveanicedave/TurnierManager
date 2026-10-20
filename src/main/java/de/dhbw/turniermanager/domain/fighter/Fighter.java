package de.dhbw.turniermanager.domain.fighter;

public class Fighter {
    private String name;
    private String dojo;

    public Fighter() {}

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
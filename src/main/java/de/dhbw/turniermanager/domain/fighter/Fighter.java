package de.dhbw.turniermanager.domain.fighter;

public class Fighter {
    private String name;
    private String dojo;
    private String weightClass = "Offen";

    public Fighter() {
        this.weightClass = "Offen";
    }

    public Fighter(String name, String dojo, String weightClass) {
        this.name = name;
        this.dojo = dojo;
        this.weightClass = (weightClass == null || weightClass.trim().isEmpty()) ? "Offen" : weightClass;
    }

    public String getName() {
        return name;
    }

    public String getDojo() {
        return dojo;
    }

    public String getWeightClass() { return weightClass; }

    public void setWeightClass(String weightClass) { this.weightClass = weightClass; }
}
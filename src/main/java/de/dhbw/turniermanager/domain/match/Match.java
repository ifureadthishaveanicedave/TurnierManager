package de.dhbw.turniermanager.domain.match;

public class Match {
    private String fighter1;
    private String fighter2;
    private String winner;

    public Match() {}

    public Match(String fighter1, String fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.winner = null;
    }

    public String getFighter1() { return fighter1; }
    public String getFighter2() { return fighter2; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) {
        this.winner = winner;
    }
}

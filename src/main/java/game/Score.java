package game;

import player.playerType.Output;

/**
 * This class represents teams score sheet
 * Created by valeri on 16.9.2017 г..
 */
public final class Score {

    private final int firstTeam;
    private final int secondTeam;

    /**
     * @param firstTeam  should be positive
     * @param secondTeam should be positive
     */
    public Score(int firstTeam, int secondTeam) {
        assert firstTeam >= 0;
        assert secondTeam >= 0;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    /**
     *
     * @return first team score
     */
    public int getFirstTeam() {
        return firstTeam;
    }

    /**
     *
     * @return second team score
     */
    public int getSecondTeam() {
        return secondTeam;
    }

    /**
     * display team scores
     */
    public void display() {
        Output.display("First team wins are: " + System.lineSeparator() + "Second team wins are: " + secondTeam);
    }
}

package game;

/**
 * This class represents the teams points for belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class TeamPoints {

    private final int firstTeam;
    private final int secondTeam;

    /**
     * @param firstTeam  should be greater than -1
     * @param secondTeam should be greater than -1
     */
    public TeamPoints(int firstTeam, int secondTeam) {
        assert firstTeam > -1;
        assert secondTeam > -1;

        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    /**
     * @return the first team points
     */
    public int getFirstTeam() {
        return firstTeam;
    }

    /**
     * @return the second team points
     */
    public int getSecondTeam() {
        return secondTeam;
    }

}

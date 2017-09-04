package player;

/**
 * This class represents team in belote game
 * Created by valeri on 25.8.2017 г..
 */
public final class Team {

    private final int team;

    /**
     * @param team should be 0 or 1
     */
    public Team(int team) {
        assert team == 0 || team == 1;
        this.team = team;
    }

    /**
     *
     * @return the team number
     */
    public int getTeam() {
        return team;
    }
}

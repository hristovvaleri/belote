package player;

import card.Card;
import declaration.Declaration;

import java.util.List;

/**
 * This class represent players in belote game
 * Created by valeri on 4.8.2017 г..
 */
public abstract class Player {

    private final Team team;
    private final List<Card> hand;
    private final String name;

    public Player(Team team, List<Card> hand, String name) {
        assert team != null;
        assert hand != null;
        assert name != null;
        assert name != "";

        this.team = team;
        this.hand = hand;
        this.name = name;
    }

    /**
     * @return the current player team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @return the current player name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the current player hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * @param currentBid should not be null
     * @return the called bid by the current player
     */
    public abstract Bid callBid(Bid currentBid);

    /**
     * @param winningCard should not be null after the first played card
     * @param playedCard  should not be when after the first played card
     * @return the called declarations by the player
     */
    public abstract List<Declaration> callDeclaration(Card winningCard
            , Card playedCard);

    /**
     * @param winningCard should not be null
     * @param bid         should not be null
     * @return the played card by the current player
     */
    public abstract Card playCard(Card winningCard, Bid bid);

}

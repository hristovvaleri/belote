package game;

import card.Card;
import java.util.List;

/**
 * This class represents the round winner and taken cards(of the round) in belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class TakenCardsAndWinner {

    private final List<Card> takenCards;
    private final int player;

    /**
     *
     * @param takenCards should not be null
     * @param player should be 0,1,2 or 3
     */
    public TakenCardsAndWinner(List<Card> takenCards, int player) {
        assert takenCards != null;
        assert player == 0 || player == 1 || player == 2 || player == 3;

        this.takenCards = takenCards;
        this.player =player;
    }

    /**
     *
     * @return the taken cards of the round
     */
    public List<Card> getTakenCards() {
        return takenCards;
    }

    /**
     *
     * @return the round winner's number
     */
    public int getRoundWinner() {
        return player;
    }
}

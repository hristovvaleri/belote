package declaration;

import card.Value;
import card.Card;
import java.util.Arrays;
import java.util.List;

/**
 * This is a class for belote declaration
 * Created by valeri on 5.8.2017 г..
 */
public final class Belote {

    private final static Value[] QUEEN_KING = new Value[]{Value.QUEEN, Value.KING};

    /**
     * @param hand        should not be null
     * @param winningCard should not be null
     * @param playedCard  should not be null
     * @return if there is a belote declaration
     */
    public static boolean check(List<Card> hand, Card winningCard, Card playedCard) {
        assert hand != null;
        assert winningCard != null;
        assert playedCard != null;

        boolean state = false;
        if (winningCard.getNormalCard().getSuit().equals(playedCard.getNormalCard().getSuit()) &&
                Arrays.asList(QUEEN_KING).contains(playedCard.getNormalCard().getValue())) {

            for (Card currentCard : hand) {
                if (currentCard.getNormalCard().getSuit().equals(playedCard.getNormalCard().getSuit()) &&
                        Arrays.asList(QUEEN_KING).contains(currentCard)) {

                    state = true;
                    break;
                }
            }
        }

        return state;
    }
}

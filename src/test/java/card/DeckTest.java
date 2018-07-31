package card;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by valeri on 6.8.2017 г..
 */
public class DeckTest {
    @Test
    public void dealCardsNotNull() {
        Deck deck = new Deck();
        List<Card> hand = new ArrayList<>();
        deck.dealCards(hand);
        assertTrue(notNull(hand));
    }

    @Test
    public void dealCardsCorrectNumberOfCards() {
        Deck deck = new Deck();
        List<Card> hand = new ArrayList<>();
        deck.dealCards(hand);
        assertEquals(3,hand.size());
    }

    private boolean notNull(List<Card> hand){
        assert  hand != null;

        boolean state = true;

        for (Card currentCard :hand){
            if(currentCard == null){
                state=false;
                break;
            }
        }

        return state;
    }

}
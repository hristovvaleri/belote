package card;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by valeri on 6.8.2017 г..
 */
public class CardTest {
    @Test
    public void getBeloteCardNotNull() {
        NormalCard normalCard = new NormalCard(Suit.CLUBS, Value.ACE);
        BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(11));
        Card card = new Card(beloteCard, normalCard);
        assertNotNull(card.getBeloteCard());
    }


    @Test
    public void getNormalCardNotNull() {
        NormalCard normalCard = new NormalCard(Suit.CLUBS, Value.ACE);
        BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(11));
        Card card = new Card(beloteCard, normalCard);
        assertNotNull(card.getNormalCard());
    }

}
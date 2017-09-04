package card;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by valeri on 4.8.2017 г..
 */
public class BeloteCardTest {
    @Test
    public void compareToSmaller() throws Exception {
        NormalCard firstNormalCard = new NormalCard(Suit.CLUBS,Value.SEVEN);
        BeloteWeight firstBeloteWeight = new BeloteWeight(14);
        NormalCard secondNormalCard = new NormalCard(Suit.CLUBS,Value.ACE);
        BeloteWeight secondBeloteWeigt = new BeloteWeight(15);
        BeloteCard firstBeloteCard = new BeloteCard(firstNormalCard,firstBeloteWeight);
        BeloteCard secondBeloteCard = new BeloteCard(secondNormalCard,secondBeloteWeigt);

        assertEquals(1,secondBeloteCard.compareTo(firstBeloteCard));
    }

    @Test
    public void compareToBigger() throws Exception {
        NormalCard firstNormalCard = new NormalCard(Suit.CLUBS,Value.SEVEN);
        BeloteWeight firtBelobeWeight = new BeloteWeight(16);
        NormalCard secondNormalCard = new NormalCard(Suit.CLUBS,Value.ACE);
        BeloteWeight secondBeloteWeigt = new BeloteWeight(15);
        BeloteCard firstCard = new BeloteCard(firstNormalCard,firtBelobeWeight);
        BeloteCard secondCard = new BeloteCard(secondNormalCard,secondBeloteWeigt);

        assertEquals(-1,secondCard.compareTo(firstCard));
    }

    @Test
    public void compareToEqual() throws Exception {
        NormalCard firstNormalCard = new NormalCard(Suit.CLUBS,Value.SEVEN);
        BeloteWeight firtBelobeWeight = new BeloteWeight(15);
        NormalCard secondNormalCard = new NormalCard(Suit.CLUBS,Value.ACE);
        BeloteWeight secondBeloteWeigt = new BeloteWeight(15);
        BeloteCard firstCard = new BeloteCard(firstNormalCard,firtBelobeWeight);
        BeloteCard secondCard = new BeloteCard(secondNormalCard,secondBeloteWeigt);

        assertEquals(0,secondCard.compareTo(firstCard));
    }

}
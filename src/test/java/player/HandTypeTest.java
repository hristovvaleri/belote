package player;

import card.Card;
import card.NormalCard;
import card.Suit;
import card.Value;
import player.playerType.HumanPlayer;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import card.BeloteCard;
import card.BeloteWeight;

/**
 * Created by valeri on 6.8.2017 г..
 */
public class HandTypeTest {
    @Test
    public void sumHand() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.addAll(getHandWithOneCard(Suit.CLUBS, Value.ACE, 11));
        hand.addAll(getHandWithOneCard(Suit.DIAMONDS, Value.NINE, 14));
        assertEquals(25, HandType.sumHandPoints(hand));
    }

    @Test
    public void getHandNotNull() throws Exception {
        List<Card> hand = getHandWithOneCard(Suit.CLUBS, Value.ACE, 11);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new HumanPlayer(new Team(1), hand, "Test"));
        assertNotNull(HandType.getBeloteHand(hand, bid).get(0));
    }

    @Test
    public void getHand() throws Exception {
        List<Card> hand = getHandWithOneCard(Suit.CLUBS, Value.ACE, 11);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new HumanPlayer(new Team(1), hand, "Test"));
        assertEquals(11, HandType.getBeloteHand(hand, bid).get(0).getBeloteCard().getWeight().getCardWeight());
    }

    private List<Card> getHandWithOneCard(Suit suit, Value value, int cardWeight) {
        assert suit != null;
        assert value != null;

        List<Card> hand = new ArrayList<>();
        NormalCard normalCard = new NormalCard(suit, value);
        BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(cardWeight));
        Card card = new Card(beloteCard, normalCard);
        hand.add(card);

        return hand;
    }
}
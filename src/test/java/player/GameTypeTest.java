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
public class GameTypeTest {
    @Test
    public void getBeloteCard() throws Exception {
        List<Card> hand = new ArrayList<>();

        Card winningCard = generateCard(Suit.CLUBS,Value.ACE,11);
        Card cardToPlay = generateCard(Suit.CLUBS,Value.NINE,14);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new HumanPlayer(new Team(1), hand, "Test"));
        hand.add(cardToPlay);
        assertEquals(cardToPlay.getBeloteCard(), GameType.getCard(hand, winningCard, bid).getBeloteCard());
    }

    @Test
    public void getCardNotNull() throws Exception {
        List<Card> hand = new ArrayList<>();

        Card winningCard = generateCard(Suit.CLUBS,Value.ACE,11);
        Card cardToPlay = generateCard(Suit.CLUBS,Value.NINE,14);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new HumanPlayer(new Team(1), hand, "Test"));
        hand.add(cardToPlay);
        assertNotNull(GameType.getCard(hand, winningCard, bid).getBeloteCard());
    }

    private Card generateCard(Suit suit, Value value, int cardWeight) {
        assert suit != null;
        assert value != null;

        NormalCard normalCard = new NormalCard(suit, value);
        BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(cardWeight));

        return new Card(beloteCard, normalCard);
    }

}
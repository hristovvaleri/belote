package player.playerType;

import card.Card;
import declaration.Declaration;
import player.Bid;
import player.BidType;
import org.junit.Test;
import card.BeloteWeight;
import card.BeloteCard;
import card.NormalCard;

import java.util.ArrayList;
import java.util.List;

import card.Suit;
import card.Value;
import player.Team;

import static org.junit.Assert.*;

/**
 * Created by valeri on 6.8.2017 г..
 */
public class ComputerPlayerTest {

    private final static int RANDOM_NUMBER = 5;

    @Test
    public void callBidNotNull() throws Exception {
        List<Card> hand = new ArrayList<>();

        Card card = generateCard(Suit.CLUBS, Value.ACE, 11);
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        for (int i = 0; i < RANDOM_NUMBER; i++) {
            hand.add(card);
        }
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new ComputerPlayer(new Team(0), new ArrayList<>(), "Test"));
        assertNotNull(computerPlayer.callBid(bid));

    }

    @Test
    public void callBid() throws Exception {
        List<Card> hand = new ArrayList<>();

        Card card = generateCard(Suit.CLUBS, Value.ACE, 11);
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        for (int i = 0; i < RANDOM_NUMBER; i++) {
            hand.add(card);
        }
        Bid bid = new Bid(BidType.PASS, 1, new ComputerPlayer(new Team(0), new ArrayList<>(), "Test"));
        assertEquals(new Bid(BidType.CLUBS, 1, computerPlayer).getBid(), computerPlayer.callBid(bid).getBid());

    }

    @Test
    public void callDeclarationNotNull() throws Exception {
        List<Card> hand = generateCards();
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        assertNotNull(computerPlayer.callDeclaration(null, null));

    }

    @Test
    public void callDeclaration() throws Exception {
        List<Card> hand = generateCards();
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        List<Declaration> declarations = new ArrayList<>();
        declarations.add(Declaration.TIERCE);
        assertEquals(declarations, computerPlayer.callDeclaration(null, null));

    }

    @Test
    public void playCardNotNull() throws Exception {
        List<Card> hand = new ArrayList<>();
        Card expected = generateCard(Suit.CLUBS, Value.ACE, 11);
        hand.addAll(getKingAndQueen(Suit.CLUBS));
        hand.add(expected);
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        Card winningCard = generateCard(Suit.CLUBS, Value.TEN, 10);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, computerPlayer);
        assertNotNull(computerPlayer.playCard(winningCard, bid));
    }

    @Test
    public void playCardForAllTrump() throws Exception {
        List<Card> hand = new ArrayList<>();
        Card expected = generateCard(Suit.CLUBS, Value.ACE, 11);
        hand.addAll(getKingAndQueen(Suit.CLUBS));
        hand.add(expected);
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        Card winningCard = generateCard(Suit.CLUBS, Value.ACE, 10);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, computerPlayer);
        assertEquals(expected, computerPlayer.playCard(winningCard, bid));
    }

    @Test
    public void playCardForNoTrump() throws Exception {
        List<Card> hand = new ArrayList<>();
        Card expected = generateCard(Suit.CLUBS, Value.EIGHT, 0);
        hand.addAll(getKingAndQueen(Suit.SPADES));
        hand.add(expected);
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        Card winningCard = generateCard(Suit.CLUBS, Value.TEN, 10);
        Bid bid = new Bid(BidType.NO_TRUMP, 1, computerPlayer);
        assertEquals(expected, computerPlayer.playCard(winningCard, bid));
    }

    @Test
    public void playCardForSuit() throws Exception {
        List<Card> hand = new ArrayList<>();
        Card expected = generateCard(Suit.CLUBS, Value.ACE, 11);
        hand.add(expected);
        hand.addAll(getKingAndQueen(Suit.SPADES));
        ComputerPlayer computerPlayer = new ComputerPlayer(new Team(0), hand, "Test");
        Card winningCard = generateCard(Suit.DIAMONDS, Value.TEN, 10);
        Bid bid = new Bid(BidType.CLUBS, 1, computerPlayer);
        assertEquals(expected, computerPlayer.playCard(winningCard, bid));
    }

    private Card generateCard(Suit suit, Value value, int cardWeight) {
        assert suit != null;
        assert value != null;

        NormalCard normalCard = new NormalCard(suit, value);
        BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(cardWeight));

        return new Card(beloteCard, normalCard);
    }

    private List<Card> getKingAndQueen(Suit suit){
        assert  suit != null;
        List<Card> hand = new ArrayList<>();
        hand.add(generateCard(suit, Value.KING, 4));
        hand.add(generateCard(suit, Value.QUEEN, 3));

        return hand;
    }


    private List<Card> generateCards() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.CLUBS, Value.ACE), new BeloteWeight(11)), new NormalCard(Suit.CLUBS, Value.ACE)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.CLUBS, Value.KING), new BeloteWeight(4)), new NormalCard(Suit.CLUBS, Value.KING)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.CLUBS, Value.QUEEN), new BeloteWeight(3)), new NormalCard(Suit.CLUBS, Value.QUEEN)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.HEARTS, Value.ACE), new BeloteWeight(11)), new NormalCard(Suit.HEARTS, Value.ACE)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.SPADES, Value.ACE), new BeloteWeight(11)), new NormalCard(Suit.SPADES, Value.ACE)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.HEARTS, Value.SEVEN), new BeloteWeight(0)), new NormalCard(Suit.HEARTS, Value.SEVEN)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.DIAMONDS, Value.KING), new BeloteWeight(4)), new NormalCard(Suit.DIAMONDS, Value.KING)));
        hand.add(new Card(new BeloteCard(new NormalCard(Suit.DIAMONDS, Value.SEVEN), new BeloteWeight(0)), new NormalCard(Suit.DIAMONDS, Value.SEVEN)));

        return hand;
    }

}
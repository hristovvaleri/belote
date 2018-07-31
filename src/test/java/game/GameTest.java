package game;

import card.*;
import player.Player;
import player.Team;
import player.playerType.ComputerPlayer;

import static org.junit.Assert.*;

import org.junit.Test;
import player.Bid;
import player.BidType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeri on 7.8.2017 г..
 */
public class GameTest {
    @Test
    public void playNotNull() {
        Deck deck = new Deck();

        List<Player> players = getPlayers();
        for (Player currentPlayer : players) {
            deck.dealCards(currentPlayer.getHand());
        }
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new ComputerPlayer(new Team(0), new ArrayList<>(), "Test"));

        assertNotNull(new Game(new Round(players)).play(0, bid));
    }

    @Test
    public void getBidNotNull() {
        List<Player> players = getPlayers();
        Round round = new Round(players);

        assertNotNull(new Game(round).chooseBid(0));

    }

    @Test
    public void getBidExpected() {
        List<Player> players = getPlayers();
        Round round = new Round(players);

        assertEquals(BidType.ALL_TRUMP, new Game(round).chooseBid(0).getBid());

    }

    private List<Player> getPlayers() {

        ComputerPlayer playerOne = new ComputerPlayer(new Team(0), getHand(Value.JACK, 20), "Test1");
        ComputerPlayer playerTwo = new ComputerPlayer(new Team(1), getHand(Value.ACE, 11), "Test2");
        ComputerPlayer playerThree = new ComputerPlayer(new Team(0), getHand(Value.KING, 4), "Test3");
        ComputerPlayer playerFour = new ComputerPlayer(new Team(1), getHand(Value.QUEEN, 3), "Test4");

        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        return players;
    }

    private List<Card> getHand(Value value, int cardWeight) {

        List<Card> hand = new ArrayList<>();
        for (int count = 0; count < 4; count++) {
            for (int i = 0; i < 3; i++) {
                Card card = new Card(new BeloteCard(new NormalCard(Constants.SUITS[i], value), new BeloteWeight(cardWeight)), new NormalCard(Constants.SUITS[i], value));
                hand.add(card);
            }

        }
        return hand;
    }

}
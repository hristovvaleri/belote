package game;

import card.*;
import player.Player;
import player.Team;
import player.playerType.ComputerPlayer;
import org.junit.Test;

import static org.junit.Assert.*;
import player.Bid;
import player.BidType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeri on 6.8.2017 г..
 */
public class RoundTest {

    @Test
    public void playNotNull() throws Exception {
        List<Player> players = getPlayers();
        Round round = new Round(players);
        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new ComputerPlayer(new Team(0), new ArrayList<>(), "Test"));
        assertNotNull(round.play(0, bid).getTakenCards());
    }

    @Test
    public void play() throws Exception {

        Bid bid = new Bid(BidType.ALL_TRUMP, 1, new ComputerPlayer(new Team(0), new ArrayList<>(), "Test"));
        List<Player> players = getPlayers();
        Round round = new Round(players);

        List<Card> takenCards = new ArrayList<>();
        TakenCardsAndWinner actual = round.play(0, bid);
        for (Card currentCard : actual.getTakenCards()) {
            takenCards.add(currentCard);
        }
        int roundWinner = 1;
        TakenCardsAndWinner winnerAndRoundCards = new TakenCardsAndWinner(takenCards, roundWinner);
        assertEquals(winnerAndRoundCards.getTakenCards(), actual.getTakenCards());
    }


    @Test
    public void getPlayerToBid() throws Exception {
        int test = 2;
        assertEquals(3, Round.getPlayerToBid(test));
    }

    @Test
    public void getPlayerToBidReverse() throws Exception {
        int test = 3;
        assertEquals(0, Round.getPlayerToBid(test));
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
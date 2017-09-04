package game;

import card.Card;
import player.Player;
import player.Bid;
import player.HandType;
import player.BidType;
import player.playerType.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single game in belote game
 * Created by valeri on 5.8.2017 г..
 */
public class Game {

    private final Round round;

    private final static int ONE_GAME = 8;
    private final static int MIN_PASSES = 3;

    /**
     * @param round should not be null
     */
    public Game(Round round) {
        assert round != null;

        this.round = round;
    }

    /**
     * @param dealer should be between 0 and 3
     * @param bid    should not be null
     * @return the Point object for the current game
     */
    public Point play(int dealer, Bid bid) {
        assert dealer > -1 && dealer < 4;
        assert bid != null;

        List<Card> firstTeam = new ArrayList<>();
        List<Card> secondTeam = new ArrayList<>();
        changeCardValues(bid);
        int roundWinner = dealer;
        TakenCardsAndWinner winnerAndRoundCards;

        for (int count = 0; count < ONE_GAME; count++) {
            winnerAndRoundCards = round.play(roundWinner, bid);
            if (winnerAndRoundCards.getRoundWinner() % 2 == 0) {
                firstTeam.addAll(winnerAndRoundCards.getTakenCards());
            } else {
                secondTeam.addAll(winnerAndRoundCards.getTakenCards());
            }
            roundWinner = winnerAndRoundCards.getRoundWinner();
        }

        return new Point(bid, firstTeam, secondTeam, roundWinner,
                bid.getCounter(), round.getFirstTeamDeclaration(), round.getSecondTeamDeclarations());
    }

    /**
     * @param dealer should be between 0 and 3
     * @return the players bid for the game
     */
    public Bid chooseBid(int dealer) {
        assert dealer > -1 && dealer < 4;

        Bid bid = new Bid(BidType.PASS, 1, round.getPlayers().get(dealer));
        int passCount = 0;
        bid = round.getPlayers().get(dealer).callBid(bid);
        int playerTurn = Round.getPlayerToBid(dealer);

        while (passCount < MIN_PASSES) {
            Bid currentBid = round.getPlayers().get(playerTurn).callBid(bid);
            if (!currentBid.getBid().equals(BidType.PASS) &&
                    currentBid.getBid().getBidPower() >= bid.getBid().getBidPower()) {

                bid = currentBid;
                passCount = 0;
            } else {
                passCount++;
            }
            Output.display("Player " + round.getPlayers().get(playerTurn).getName() + " said " + currentBid.getBid());
            playerTurn = Round.getPlayerToBid(playerTurn);
        }

        return bid;
    }

    private void changeCardValues(Bid bid) {
        assert bid != null;

        for (Player currentPlayer : round.getPlayers()) {
            List<Card> copy = getCardValues(currentPlayer.getHand(), bid);
            currentPlayer.getHand().clear();
            currentPlayer.getHand().addAll(copy);
            assert currentPlayer.getHand() != null;
        }

        assert bid != null;
        assert invariant(round.getPlayers());
    }

    /**
     * @param players should not be null
     */
    public static void clearHands(List<Player> players) {
        assert players != null;

        for (Player currentPlayer : players) {
            currentPlayer.getHand().clear();
        }

        assert invariant(players);
    }

    private static List<Card> getCardValues(List<Card> hand, Bid bid) {
        assert hand != null;
        assert bid != null;

        List<Card> handCopy = new ArrayList<>(hand);
        hand.clear();

        return HandType.getBeloteHand(handCopy, bid);
    }

    private static boolean invariant(List<Player> players) {
        assert players != null;

        boolean state = true;
        for (Player currentPlayer : players) {
            if (currentPlayer.getHand() == null) {
                state = false;
                break;
            }
        }

        return state;
    }
}

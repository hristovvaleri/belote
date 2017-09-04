package game;

import card.Deck;
import player.Player;
import player.Team;
import player.playerType.ComputerPlayer;
import player.playerType.HumanPlayer;
import player.Bid;
import player.BidType;
import player.playerType.Input;
import player.playerType.Output;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent playing a belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class PlayGame {
    public static void main(String[] args) {

        List<Player> players = PlayGame.generatePlayers();
        Deck deck = new Deck();
        int firstTeamPoints = 0;
        int secondTeamPoints = 0;
        int dealer = 0;
        while (firstTeamPoints <= 151 || secondTeamPoints <= 151) {
            for (Player currentPlayer : players) {
                deck.dealCards(currentPlayer.getHand());
            }
            for (Player currentPlayer : players) {
                deck.dealCards(currentPlayer.getHand());
            }
            Round round = new Round(players);
            Game game = new Game(round);
            Bid bid = game.chooseBid(dealer);
            if (!bid.getBid().equals(BidType.PASS)) {
                for (Player currentPlayer : players) {
                    deck.dealCards(currentPlayer.getHand());
                }
                Point point = game.play(dealer, bid);
                TeamPoints teamPoints = point.getPoints();
                firstTeamPoints += Point.calculatePoints(teamPoints.getFirstTeam());
                secondTeamPoints += Point.calculatePoints(teamPoints.getSecondTeam());
            }
            Game.clearHands(players);
            dealer = Round.getPlayerToBid(dealer);
        }
        Output.display(firstTeamPoints + System.lineSeparator() + secondTeamPoints);
    }

    private static List<Player> generatePlayers() {

        List<Player> players = new ArrayList<>();
        Output.display("For human player choose 0 and for computer 1");
        for (int count = 0; count < PLAYERS_SIZE; count++) {
            int choice;
            do {
                Output.display("Choose player");
                choice = Input.getInput();
            } while (choice != 0 && choice != 1);
            Player player = choice == 0 ? new HumanPlayer(new Team(count % 2), new ArrayList<>(), "Player " + count)
                    : new ComputerPlayer(new Team(count % 2), new ArrayList<>(), "Player " + count);
            players.add(player);
        }

        return players;
    }

    private final static int PLAYERS_SIZE = 4;
}

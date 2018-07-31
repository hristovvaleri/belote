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
 * This class represents playing a belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class BeloteCardGame {

    private final static int PLAYERS_SIZE = 4;
    private final static int GAME_POINTS = 151;

    /**
     * @return the teams score sheet
     */
    public static Score play() {
        Score globalScore = new Score(0, 0);
        int state;
        int dealer = 0;
        List<Player> players = generatePlayers();
        do {
            Score currentScore = oneGame(players, dealer);
            globalScore = new Score(globalScore.getFirstTeam() + currentScore.getFirstTeam(),
                    globalScore.getSecondTeam() + currentScore.getSecondTeam());

            globalScore.display();
            dealer = Round.getPlayerToBid(dealer);
            state = playAnotherGame();
        } while (state != 1);

        return globalScore;
    }

    private static Score oneGame(List<Player> players, int dealer) {
        assert players != null;
        assert dealer >= 0 && dealer <= 4;

        Deck deck = new Deck();
        int firstTeamPoints = 0;
        int secondTeamPoints = 0;
        while (firstTeamPoints <= GAME_POINTS || secondTeamPoints <= GAME_POINTS) {
            deal(players, deck);
            deal(players, deck);
            Game game = new Game(new Round(players));
            dealer = Round.getPlayerToBid(dealer);
            Bid bid = game.chooseBid(dealer);
            if (!bid.getBid().equals(BidType.PASS)) {
                deal(players, deck);
                TeamPoints teamPoints = game.play(dealer, bid).getPoints();
                firstTeamPoints += Point.calculatePoints(teamPoints.getFirstTeam());
                secondTeamPoints += Point.calculatePoints(teamPoints.getSecondTeam());
            }
            Game.clearHands(players);
        }

        return getWinner(firstTeamPoints, secondTeamPoints);
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
            Player player = choice == 0 ?
                    new HumanPlayer(new Team(count % 2), new ArrayList<>(), "Player " + count) :
                    new ComputerPlayer(new Team(count % 2), new ArrayList<>(), "Player " + count);
            players.add(player);
        }

        return players;
    }

    private static void deal(List<Player> players, Deck deck) {
        assert players != null;
        assert deck != null;

        for (Player currentPlayer : players) {
            deck.dealCards(currentPlayer.getHand());
        }

    }

    private static Score getWinner(int firstTeamPoints, int secondTeamPoints) {
        assert firstTeamPoints >= 0;
        assert secondTeamPoints >= 0;

        return firstTeamPoints > secondTeamPoints ? new Score(1, 0) : new Score(0, 1);
    }

    private static int playAnotherGame() {
        Output.display("If you want to play another game type 1.If you want to quit type 0");
        int state;
        do {
            state = Input.getInput();
        } while (state != 0 && state != 1);

        return state;
    }


}

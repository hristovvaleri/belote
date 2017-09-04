package game;

import card.Card;
import declaration.Declaration;
import player.Player;
import player.Bid;
import player.playerType.ComputerPlayer;
import player.playerType.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single round in belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class Round {

    private final List<Player> players;

    private final List<Declaration> firstTeamDeclaration = new ArrayList<>();
    private final List<Declaration> secondTeamDeclarations = new ArrayList<>();

    private final static int MIN_PLAYER = -1;
    private final static int MAX_PLAYER = 4;
    private final static int OTHER_PLAYERS = 3;

    /**
     * @param players should not be null
     */
    public Round(List<Player> players) {
        assert players != null;
        this.players = players;
    }

    /**
     * @param dealer should be between 0 and 3
     * @param bid    should not be null
     * @return the current round winner and taken cards
     */
    public TakenCardsAndWinner play(int dealer, Bid bid) {
        assert dealer > MIN_PLAYER && dealer < MAX_PLAYER;
        assert bid != null;

        int playerTurn = dealer;
        int roundWinner = playerTurn;

        Card winningCard = playFirstCard(playerTurn, bid);
        Output.display("Player:" + players.get(playerTurn).getName() + " played card: " + winningCard.toString());
        players.get(playerTurn).getHand().remove(winningCard);
        callDeclarations(playerTurn, winningCard, winningCard);

        return nextPlayersToPlay(playerTurn, winningCard, roundWinner, bid);
    }

    private TakenCardsAndWinner nextPlayersToPlay(int playerTurn, Card winningCard, int roundWinner, Bid bid) {
        assert playerTurn > MIN_PLAYER && playerTurn < MAX_PLAYER;
        assert winningCard != null;
        assert roundWinner > MIN_PLAYER && roundWinner < MAX_PLAYER;
        assert bid != null;

        Card playedCard;
        List<Card> takenCards = new ArrayList<>();

        for (int count = 0; count < OTHER_PLAYERS; count++) {
            playerTurn = getPlayerToBid(playerTurn);
            playedCard = players.get(playerTurn).playCard(winningCard, bid);
            Output.display("Player:" + players.get(playerTurn).getName() + " played card: " + playedCard.toString());

            callDeclarations(playerTurn, winningCard, playedCard);

            if (isGreater(winningCard, playedCard)) {
                takenCards.add(winningCard);
                winningCard = playedCard;
                roundWinner = playerTurn;
            } else {
                takenCards.add(playedCard);
            }
        }
        takenCards.add(winningCard);

        return new TakenCardsAndWinner(takenCards, roundWinner);
    }

    /**
     * @return the player of the round
     */
    public List<Player> getPlayers() {
        return players;
    }


    /**
     * @param dealer should be between 0 and 3
     * @return the next player to bid
     */
    public static int getPlayerToBid(int dealer) {
        assert dealer > MIN_PLAYER && dealer < MAX_PLAYER;

        int number = dealer + 1;

        return number < MAX_PLAYER ? number : 0;
    }

    private boolean isGreater(Card winningCard, Card playedCard) {
        assert winningCard != null;
        assert playedCard != null;

        return winningCard.getBeloteCard().compareTo(playedCard.getBeloteCard()) < 0 &&
                winningCard.getNormalCard().getSuit().equals(playedCard.getNormalCard().getSuit());
    }

    private boolean callDeclarations(int playerTurn, Card winningCard, Card playedCard) {
        assert playerTurn > MIN_PLAYER && playerTurn < MAX_PLAYER;

        List<Declaration> declarations = playerTurn % 2 == 0 ? firstTeamDeclaration : secondTeamDeclarations;

        return declarations.addAll(Declaration.getDeclaration(players.get(playerTurn).getHand(),
                winningCard, playedCard));
    }

    private Card playFirstCard(int playerTurn, Bid bid) {
        assert playerTurn > MIN_PLAYER;
        assert playerTurn < MAX_PLAYER;
        assert bid != null;

        Card playedCard;
        if (players.get(playerTurn) instanceof ComputerPlayer) {
            playedCard = players.get(playerTurn).getHand().get(0);
        } else {
            playedCard = players.get(playerTurn).playCard(players.get(playerTurn).getHand().get(0), bid);
        }

        return playedCard;
    }

    /**
     * @return first team declarations
     */
    public List<Declaration> getFirstTeamDeclaration() {
        return firstTeamDeclaration;
    }

    /**
     * @return second team declarations
     */
    public List<Declaration> getSecondTeamDeclarations() {
        return secondTeamDeclarations;
    }

}
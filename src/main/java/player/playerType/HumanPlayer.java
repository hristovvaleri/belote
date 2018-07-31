package player.playerType;

import card.Card;
import declaration.Declaration;
import player.Player;
import player.BidType;

import java.util.Arrays;
import java.util.List;

import player.Bid;
import player.Team;

/**
 * This class represent the human player for belote game
 * Created by valeri on 5.8.2017 г..
 */

public final class HumanPlayer extends Player {

    /**
     * @param team should not be null
     * @param hand should not be null
     */
    public HumanPlayer(Team team, List<Card> hand, String name) {
        super(team, hand, name);
    }

    @Override
    public Bid callBid(Bid bid) {
        assert bid != null;

        Output.display("Current bid is: " + bid.getBid().name() + " counter is: " + bid.getCounter());
        Output.display("Your cards are: " + System.lineSeparator() + super.getHand().toString());
        Output.display("Choose a bid" + System.lineSeparator() + Arrays.toString(BidType.ALL_BIDS));

        int choice;
        do {
            choice = Input.getInput();
        } while (!isBidValid(choice, bid));

        BidType currentBid = BidType.ALL_BIDS[choice];
        Output.display("Choose counter");
        do {
            choice = Input.getInput();
        } while (choice < 1 || choice == 3 || choice > 4);

        return new Bid(currentBid, choice, this);
    }

    @Override
    public List<Declaration> callDeclaration(Card winningCard, Card playedCard) {
        assert winningCard != null;
        assert playedCard != null;

        List<Declaration> declarations;

        Output.display("You're cards are: " + System.lineSeparator() + super.getHand().toString());
        Output.display("The winning card is(was) - " + winningCard.toString());
        Output.display("You played card - " + playedCard.toString());

        declarations = Declaration.getDeclaration(getHand(), winningCard, playedCard);
        Output.display("You declared " + declarations.toString());

        return declarations;
    }

    @Override
    public Card playCard(Card winningCard, Bid bid) {
        assert winningCard != null;
        assert bid != null;

        Card playedCard;

        List<Card> cardsCanPlay = getCardsToPlay(winningCard, bid);

        if (cardsCanPlay.size() == 0) {
            cardsCanPlay = super.getHand();
        }
        Output.display("Current bid is: " + bid.getBid().name());
        if (winningCard.getNormalCard().equals(this.getHand().get(0).getNormalCard())) {
            Output.display("Play first card in the round");
        } else {
            Output.display("Winning card is: " + winningCard.toString());
        }
        Output.display("You're cards are " + System.lineSeparator() + cardsCanPlay.toString());
        Output.display("Play a card");

        int choice;
        do {
            choice = Input.getInput();
        } while (choice < 0);

        playedCard = cardsCanPlay.get(choice);
        super.getHand().remove(playedCard);

        return playedCard;

    }

    private List<Card> getCardsToPlay(Card winningCard, Bid bid) {
        assert winningCard != null;
        assert bid != null;

        List<Card> cardsCanPlay;
        if (winningCard.getNormalCard().equals(this.getHand().get(0).getNormalCard())) {
            cardsCanPlay = this.getHand();
        } else {
            cardsCanPlay = new HumanPlayerCards(this).getCards(winningCard, bid);
        }
        return cardsCanPlay;
    }

    private boolean isBidValid(int choice, Bid bid) {
        assert bid != null;

        return choice == 0 || (choice > bid.getBid().getBidPower() && choice < BidType.ALL_BIDS.length);
    }

}

package player.playerType;

import card.Card;
import player.Bid;
import player.BidType;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represent the correct cards to play by the hummn player in belote game
 * Created by valeri on 26.8.2017 г..
 */
public final class HumanPlayerCards {

    private final Player humanPlayer;

    /**
     * @param humanPlayer should not be null and should be a human player
     */
    public HumanPlayerCards(Player humanPlayer) {
        assert humanPlayer != null;
        assert humanPlayer instanceof HumanPlayer;

        this.humanPlayer = humanPlayer;
    }

    /**
     *
     * @param winningCard should not be null
     * @param bid should not be null
     * @return the correct cards to play
     */
    public List<Card> getCards(Card winningCard, Bid bid) {
        assert winningCard != null;
        assert bid != null;

        List<Card> cards = null;
        if (Arrays.asList(BidType.SUIT_BIDS).contains(bid.getBid())) {
            cards = suit(winningCard, bid);
        } else if (bid.getBid().equals(BidType.ALL_TRUMP)) {
            cards = trump(winningCard);
        } else if (bid.getBid().equals(BidType.NO_TRUMP)) {
            cards = noTrump(winningCard);
        }

        return cards;
    }

    private List<Card> trump(Card winningCard) {
        assert winningCard != null;

        List<Card> higherCards = new ArrayList<>();
        List<Card> lowerCards = new ArrayList<>();

        for (Card currentCard : humanPlayer.getHand()) {
            if (currentCard.getNormalCard().getSuit().equals(winningCard.getNormalCard().getSuit())) {
                lowerCards.add(currentCard);
                if (currentCard.getBeloteCard().compareTo(winningCard.getBeloteCard()) > 0) {
                    higherCards.add(currentCard);
                }
            }
        }

        return getCorrectCards(higherCards, lowerCards);
    }

    private List<Card> noTrump(Card winningCard) {
        assert winningCard != null;

        List<Card> cards = new ArrayList<>();
        for (Card currentCard : humanPlayer.getHand()) {
            if (currentCard.getNormalCard().getSuit().equals(winningCard.getNormalCard().getSuit())) {
                cards.add(currentCard);
            }
        }

        return cards;
    }

    private List<Card> suit(Card currentCard, Bid bid) {
        assert currentCard != null;
        assert bid != null;

        List<Card> cards = new ArrayList<>();

        if (currentCard.getNormalCard().getSuit().name().matches(bid.getBid().name())) {
            cards.addAll(trump(currentCard));
        } else {
            cards.addAll(noTrump(currentCard));
            if (cards.size() != 0 && needSuitRaise(currentCard, bid, cards.get(0))) {
                cards.addAll(suitRaise(currentCard, bid));
            }

        }
        return cards;
    }

    private static boolean needSuitRaise(Card currentCard, Bid bid, Card playedCard) {
        assert currentCard != null;
        assert bid != null;
        assert playedCard != null;

        return !currentCard.getCardSuit().equals(playedCard.getCardSuit())
                && !currentCard.getCardSuit().name().equals(bid.getBid().name());
    }

    private List<Card> suitRaise(Card playedCard, Bid bid) {
        assert playedCard != null;
        assert bid != null;

        List<Card> cards = new ArrayList<>();
        for (Card currentCard : humanPlayer.getHand()) {

            if (bid.getBid().name().equals(currentCard.getCardSuit().name())) {
                cards.add(currentCard);
            }
        }

        return cards;
    }

    private List<Card> getCorrectCards(List<Card> higher, List<Card> lower) {
        assert higher != null;
        assert lower != null;

        return higher.size() == 0 ? lower : higher;
    }
}

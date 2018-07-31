package player;

import card.Card;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents all the game types in belote game
 * Created by valeri on 5.8.2017 г..
 */
public final class GameType {

    /**
     * @param hand        should not be null
     * @param winningCard should not be null
     * @param bid         should not be null
     * @return the played card for a current bid
     */
    public static Card getCard(List<Card> hand, Card winningCard, Bid bid) {
        assert hand != null;
        assert winningCard != null;
        assert bid != null;

        Card playedCard = null;
        if (Arrays.asList(BidType.SUIT_BIDS).contains(bid.getBid())) {
            playedCard = suit(hand, winningCard, bid);
        } else if (bid.getBid().equals(BidType.ALL_TRUMP)) {
            playedCard = trump(hand, winningCard);
        } else if (bid.getBid().equals(BidType.NO_TRUMP)) {
            playedCard = noTrump(hand, winningCard);
        }

        return playedCard;
    }

    private static Card trump(List<Card> hand, Card winningCard) {
        assert hand != null;
        assert winningCard != null;

        Card playedCard = hand.get(0);

        for (Card currentCard : hand) {
            if (currentCard.getNormalCard().getSuit().equals(winningCard.getNormalCard().getSuit())) {
                playedCard = currentCard;
                if (currentCard.getBeloteCard().compareTo(winningCard.getBeloteCard()) > 0) {
                    playedCard = currentCard;
                    break;
                }
            }
        }

        return playedCard;
    }

    private static Card noTrump(List<Card> hand, Card winningCard) {
        assert hand != null;
        assert winningCard != null;

        Card playedCard = hand.get(0);

        for (Card currentCard : hand) {
            if (currentCard.getNormalCard().getSuit().equals(winningCard.getNormalCard().getSuit())) {
                playedCard = currentCard;
                break;
            }
        }

        return playedCard;
    }

    private static Card suit(List<Card> hand, Card currentCard, Bid bid) {
        assert hand != null;
        assert currentCard != null;
        assert bid != null;

        Card playedCard;

        if (currentCard.getNormalCard().getSuit().name().matches(bid.getBid().name())) {
            playedCard = trump(hand, currentCard);
        } else {
            playedCard = noTrump(hand, currentCard);

            if (needSuitRaise(currentCard, bid, playedCard)) {
                playedCard = suitRaise(hand, currentCard, bid);
            }

        }
        return playedCard;
    }

    private static boolean needSuitRaise(Card currentCard, Bid bid, Card playedCard) {
        assert currentCard != null;
        assert bid != null;
        assert playedCard != null;

        return !currentCard.getCardSuit().equals(playedCard.getCardSuit())
                && !currentCard.getCardSuit().name().equals(bid.getBid().name());
    }

    private static Card suitRaise(List<Card> hand, Card playedCard, Bid bid) {
        assert hand != null;
        assert playedCard != null;
        assert bid != null;

        for (Card currentCard : hand) {

            if (bid.getBid().name().equals(currentCard.getCardSuit().name())) {
                playedCard = currentCard;
                break;
            }
        }

        return playedCard;
    }
}

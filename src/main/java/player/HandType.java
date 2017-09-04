package player;

import card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents all belote card weight changes
 * Created by valeri on 4.8.2017 г..
 */
public final class HandType {

    private static BidType[] SUITS = new BidType[]{BidType.CLUBS, BidType.DIAMONDS, BidType.HEARTS, BidType.SPADES};

    /**
     * @param hand should not be null
     * @return the sum of all card in the hand by their weight
     */
    public static int sumHandPoints(List<Card> hand) {
        assert hand != null;
        int sum = 0;

        for (Card card : hand) {
            sum += card.getBeloteCard().getWeight().getCardWeight();
        }
        return sum;
    }

    /**
     * @param hand should not be null
     * @param bid  should not be null
     * @return the player cards for belote game
     */
    public static List<Card> getBeloteHand(List<Card> hand, Bid bid) {
        assert hand != null;
        assert bid != null;

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            cards.add(new Card(new BeloteCard(hand.get(i).getNormalCard(),
                    new BeloteWeight(getWeight(hand.get(i).getNormalCard(), bid))),
                    hand.get(i).getNormalCard()));
        }

        return cards;
    }


    private static int getWeight(NormalCard currentCard, Bid bid) {
        assert currentCard != null;
        assert bid != null;

        int value = 0;
        for (int count = 0; count < Constants.VALUES.length; count++) {

            if (bid.getBid().equals(BidType.ALL_TRUMP) && isCurrentValue(currentCard, Constants.VALUES[count])) {
                value = Constants.TRUMP_VALUES[count];
                break;
            } else if (bid.getBid().equals(BidType.NO_TRUMP) && isCurrentValue(currentCard, Constants.VALUES[count])) {
                value = Constants.NOT_TRUMP_VALUES[count];
                break;
            } else if (Arrays.asList(SUITS).contains(bid.getBid()) && isCurrentValue(currentCard, Constants.VALUES[count])) {

                if (currentCard.getSuit().name().matches(bid.getBid().name())) {
                    value = Constants.TRUMP_VALUES[count];
                    break;
                } else {
                    value = Constants.NOT_TRUMP_VALUES[count];
                    break;
                }
            }
        }

        return value;
    }

    private static boolean isCurrentValue(NormalCard card, Value currentValue) {
        assert card != null;
        assert currentValue != null;

        return card.getValue().equals(currentValue);
    }

}

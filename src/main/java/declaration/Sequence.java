package declaration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;

import java.util.stream.Collectors;

/**
 * This is a class for sequence declaration
 * Created by valeri on 5.8.2017 г..
 */
public final class Sequence {

    private final static int tierce = 3;
    private final static int fifties = 4;
    private final static int hundreds = 5;

    /**
     * @param hand should not be null
     * @return the sequence declaration type
     */
    public static List<Declaration> get(List<Card> hand) {
        assert hand != null;

        List<Declaration> sequence = new ArrayList<>();

        List<Card> handCopy = new ArrayList<>(hand);
        Collections.sort(handCopy, (cardOne, cardTwo) -> cardOne.getBeloteCard().compareTo(cardTwo.getBeloteCard()));

        for (Card currentCard : handCopy) {
            handCopy = getSameSuitCards(handCopy, currentCard);

            if (handCopy.size() >= tierce) {
                sequence.add(determineSequence(handCopy.size()));
                sequence.remove(null);
            }

            handCopy = removeCheckedCards(hand, currentCard);
        }

        return sequence;
    }

    private static List<Card> getSameSuitCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().
                filter(card -> card.getNormalCard().getSuit().equals(currentCard.getNormalCard().getSuit()))
                .collect(Collectors.toList());
    }

    private static List<Card> removeCheckedCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().filter(card -> !card.getNormalCard().getSuit().equals(currentCard.getNormalCard().getSuit()))
                .collect(Collectors.toList());
    }

    private static Declaration determineSequence(int equalSuitNumber) {
        Declaration sequence = null;

        if (equalSuitNumber == tierce) {
            sequence = Declaration.TIERCE;
        } else if (equalSuitNumber == fifties) {
            sequence = Declaration.FIFTIES;
        } else if (equalSuitNumber == hundreds) {
            sequence = Declaration.HUNDREDS_SEQUENCE;
        }

        return sequence;
    }
}

package declaration;

import java.util.*;

import card.Card;

import java.util.stream.Collectors;

/**
 * This is a class for sequence declaration
 * Created by valeri on 5.8.2017 г..
 */
public final class Sequence {

    private final static int TIERCE = 3;
    private final static int FIFTIES = 4;
    private final static int HUNDREDS = 5;

    /**
     * @param hand should not be null
     * @return the sequence declaration type
     */
    public static List<Declaration> get(List<Card> hand) {
        assert hand != null;

        List<Declaration> sequence = new ArrayList<>();

        List<Card> handCopy = new ArrayList<>(hand);
        Collections.sort(handCopy, Comparator.comparing(Card::getBeloteCard));

        for (Card currentCard : handCopy) {
            handCopy = getSameSuitCards(handCopy, currentCard);

            if (handCopy.size() >= TIERCE) {
                Optional<Declaration> declaration = determineSequence(handCopy.size());
                declaration.ifPresent(sequence::add);
            }

            handCopy = removeCheckedCards(hand, currentCard);
        }

        return sequence;
    }

    private static List<Card> getSameSuitCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().
                filter(card -> card.getCardSuit().equals(currentCard.getCardSuit()))
                .collect(Collectors.toList());
    }

    private static List<Card> removeCheckedCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().filter(card -> !card.getCardSuit().equals(currentCard.getCardSuit()))
                .collect(Collectors.toList());
    }

    private static Optional<Declaration> determineSequence(int equalSuitNumber) {
        Declaration sequence = null;

        if (equalSuitNumber == TIERCE) {
            sequence = Declaration.TIERCE;
        } else if (equalSuitNumber == FIFTIES) {
            sequence = Declaration.FIFTIES;
        } else if (equalSuitNumber == HUNDREDS) {
            sequence = Declaration.HUNDREDS_SEQUENCE;
        }

        return Optional.ofNullable(sequence);
    }
}

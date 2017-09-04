package declaration;

import card.Card;
import card.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a class for carre declaration
 * Created by valeri on 14.5.2017 г..
 */
public final class Carre {

    private final static int CARRE = 4;
    private final static Value hundredCarreValues[] = new Value[]{Value.QUEEN,
            Value.KING, Value.ACE, Value.TEN};

    /**
     * @param hand should not be null
     * @return the carre declaration type
     */
    public static List<Declaration> get(List<Card> hand) {
        assert hand != null;

        List<Declaration> carre = new ArrayList<>();
        List<Card> handCopy = new ArrayList<>(hand);

        for (Card currentCard : handCopy) {

            handCopy = getSameCards(handCopy, currentCard);
            if (handCopy.size() == CARRE) {
                carre.add(determineCarre(currentCard));
                carre.remove(null);
            }
            handCopy = removeCheckedCards(handCopy, currentCard);
        }

        return carre;
    }

    private static List<Card> getSameCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().
                filter(card -> card.getNormalCard().getValue().equals(currentCard.getNormalCard().getValue()))
                .collect(Collectors.toList());
    }

    private static List<Card> removeCheckedCards(List<Card> hand, Card currentCard) {
        assert hand != null;
        assert currentCard != null;

        return hand.stream().
                filter(card -> !card.getNormalCard().getValue().equals(currentCard.getNormalCard().getValue()))
                .collect(Collectors.toList());
    }

    private static Declaration determineCarre(Card card) {
        assert card != null;

        Declaration carreEnum = null;

        if (card.getNormalCard().getValue().equals(Value.JACK)) {
            carreEnum = Declaration.TWOHUNDREDS;
        } else if (card.getNormalCard().getValue().equals(Value.NINE)) {
            carreEnum = Declaration.HUNDRED_AND_HALF;
        } else if (Arrays.asList(hundredCarreValues).contains(card.getNormalCard().getValue())) {
            carreEnum = Declaration.HUNDREDS_CARRE;
        }

        return carreEnum;
    }


}

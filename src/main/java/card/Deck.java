package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a normal card deck
 * Created by valeri on 4.8.2017 г..
 */
public final class Deck {

    private final List<Card> deck = new ArrayList<>();
    private int position = 0;

    /**
     * Creates a new normal deck
     */
    public Deck() {
        for (int i = Constants.CLUBS; i < Constants.SPADES; i++) {
            for (int j = Constants.SEVEN; j <= Constants.ACE; j++) {
                NormalCard normalCard = new NormalCard(Constants.SUITS[i], Constants.VALUES[j]);
                BeloteCard beloteCard = new BeloteCard(normalCard, new BeloteWeight(0));
                deck.add(new Card(beloteCard, normalCard));
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * @param hand should not be null
     */
    public void dealCards(List<Card> hand) {
        assert hand != null;
        if (hand.size() == 0 && position == 20) {
            position = 0;
            split();
        }

        if (hand.size() == 0 || hand.size() == 5) {
            deal3Cards(hand);
        } else {
            deal2Cards(hand);
        }

        assert invariant(hand);
    }

    private void deal3Cards(List<Card> hand) {
        assert hand != null;

        for (int j = 0; j < 3; j++) {
            hand.add(getFromDeck());
        }

        assert invariant(hand);
    }

    private void deal2Cards(List<Card> hand) {
        assert hand != null;
        for (int j = 0; j < 2; j++) {
            hand.add(getFromDeck());
        }

        assert invariant(hand);
    }


    private Card getFromDeck() {

        Card card = deck.get(position);
        if (position < 31) {
            position++;
        } else {
            position = 0;
            split();
        }
        return card;
    }

    private boolean invariant(List<Card> hand) {
        assert hand != null;

        boolean state = true;
        for (Card currentCard : hand) {
            if (currentCard == null) {
                state = false;
                break;
            }
        }

        return state;
    }

    public void split() {
        assert invariant(deck);

        Collections.shuffle(deck);

        assert invariant(deck);
    }
}

package card;

/**
 * This class represent a card for some game
 * Created by valeri on 6.8.2017 г..
 */
public final class Card {

    private final BeloteCard beloteCard;
    private final NormalCard normalCard;

    /**
     * @param beloteCard should not be null
     * @param normalCard should not be null
     */
    public Card(BeloteCard beloteCard, NormalCard normalCard) {
        assert beloteCard != null;
        assert normalCard != null;

        this.beloteCard = beloteCard;
        this.normalCard = normalCard;
    }

    /**
     * @return the current belote card
     */
    public BeloteCard getBeloteCard() {
        return beloteCard;
    }

    /**
     * @return the current normal card
     */
    public NormalCard getNormalCard() {
        return normalCard;
    }

    /**
     * @return the current card suit
     */
    public Suit getCardSuit() {
        return normalCard.getSuit();
    }

    /**
     * @return the current card value
     */
    public Value getCardValue() {
        return beloteCard.getValue();
    }

    /**
     * @return the card information
     */
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append(normalCard.toString());

        return output.toString();
    }
}

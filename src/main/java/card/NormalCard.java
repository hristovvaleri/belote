package card;

/**
 * This class represents one normal card f
 * Created by valeri on 4.8.2017 г..
 */
public final class NormalCard {

    private final Suit suit;
    private final Value value;

    /**
     *
     * @param suit should not be null
     * @param value should not be null
     */
    public NormalCard(Suit suit, Value value) {
        assert suit != null;
        assert value != null;

        this.suit = suit;
        this.value = value;
    }

    /**
     *
     * @return the suit of the current card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     *
     * @return the value of the current card
     */
    public Value getValue() {
        return value;
    }

    /**
     *
     * @return the information of the current card
     */
    public String toString(){
        StringBuilder output = new StringBuilder();

        output.append("card suit is ").append(suit)
                .append(": card value is ").append(value);

        return output.toString();
    }
}

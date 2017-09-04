package card;

/**
 * This class represents a belote playing card
 * Created by valeri on 4.8.2017 г..
 */
public final class BeloteCard implements Comparable<BeloteCard>   {

    private final NormalCard card;
    private final BeloteWeight weight;

    /**
     * @param card   should not be null
     * @param weight should not be null
     */
    public BeloteCard(NormalCard card, BeloteWeight weight) {
        assert card != null;
        assert weight != null;

        this.card = card;
        this.weight = weight;
    }

    @Override
    public int compareTo(BeloteCard comparedCard) {
        assert comparedCard != null;

        return this.getWeight().compareTo(comparedCard.getWeight());
    }

    /**
     *
     * @return the current card weight
     */
    public BeloteWeight getWeight() {
        return weight;
    }

    /**
     *
     * @return the belote card value
     */
    public Value getValue(){
        return card.getValue();
    }

    /**
     *
     * @return the belote card suit
     */
    public Suit getSuit(){
        return card.getSuit();
    }

    /**
     *
     * @return the card information
     */
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append(card.toString())
                .append(weight.toString())
                .append(System.lineSeparator());

        return output.toString();
    }
}

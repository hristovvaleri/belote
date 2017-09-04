package card;

/**
 * This class represents the card weight of a belote card
 * Created by valeri on 4.8.2017 г..
 */
public final class BeloteWeight implements Comparable<BeloteWeight> {

    private final int cardWeight;

    private final static int MIN_VALUE = 0;
    private final static int MAX_VALUE = 20;

    /**
     *
     * @param cardWeight should be greater than the min value of a belote card
     *                   and smaller than the max value of it.
     */
    public BeloteWeight(int cardWeight) {
        assert cardWeight >= MIN_VALUE;
        assert cardWeight <= MAX_VALUE;

        this.cardWeight = cardWeight;
    }

    /**
     *
     * @return the weight of the current card
     */
    public int getCardWeight() {
        return cardWeight;
    }

    @Override
    public int compareTo(BeloteWeight comparedCard) {
        assert comparedCard != null;

        int state = 0;
        if( this.getCardWeight() > comparedCard.getCardWeight()){
            state = 1;
        }
        if(this.getCardWeight() < comparedCard.getCardWeight()){
            state = -1;
        }

        return state;
    }

    public String toString(){
        StringBuilder output = new StringBuilder();

        output.append(" Weight is: ").append(cardWeight);

        return output.toString();
    }
}

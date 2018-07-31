package player;

/**
 * This class represents all bid type for belote game
 * Created by valeri on 4.8.2017 г..
 */
public enum BidType {
    ALL_TRUMP(6),
    NO_TRUMP(5),
    SPADES(4),
    HEARTS(3),
    DIAMONDS(2),
    CLUBS(1),
    PASS(0);

    private final int bidPower;

    public final static BidType ALL_BIDS[] = {PASS, CLUBS, DIAMONDS, HEARTS, SPADES, NO_TRUMP, ALL_TRUMP};
    public final static BidType SUIT_BIDS[] = {CLUBS, DIAMONDS, HEARTS, SPADES};

    private final static int MIN_VALUE = 0;
    private final static int MAX_VALUE = 6;

    /**
     * @param bidPower should ne greater than the min value and smalled than the max value
     */
    BidType(int bidPower) {
        assert bidPower >= MIN_VALUE;
        assert bidPower <= MAX_VALUE;

        this.bidPower = bidPower;
    }

    /**
     * @return the bid power of the current bid
     */
    public int getBidPower() {
        return bidPower;
    }
}

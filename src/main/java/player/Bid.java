package player;

/**
 * This class represent the bid for belote game
 * Created by valeri on 4.8.2017 г..
 */
public final class Bid {

    private final BidType bid;
    private final int counter;
    private final Player player;

    /**
     * @param bid     should not be null
     * @param counter should be 0,2 or 4
     * @param player  should not be null
     */
    public Bid(BidType bid, int counter, Player player) {
        assert bid != null;
        assert counter == 1 || counter == 2 || counter == 4;
        assert player != null;

        this.bid = bid;
        this.counter = counter;
        this.player = player;
    }

    /**
     * @return the called bid
     */
    public BidType getBid() {
        return bid;
    }

    /**
     * @return the counter multiplication
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @return the bid player information
     */
    public Player getPlayer() {
        return player;
    }
}

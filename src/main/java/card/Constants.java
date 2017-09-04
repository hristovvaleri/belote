package card;

/**
 * This class represents needed constants for the deck
 * Created by valeri on 4.8.2017 г..
 */
public final class Constants {

    public final static Suit[] SUITS = {Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES};

    public final static Value[] VALUES = {Value.SEVEN, Value.EIGHT, Value.NINE,
            Value.TEN, Value.JACK, Value.QUEEN,
            Value.KING, Value.ACE};

    public final static int[] NOT_TRUMP_VALUES =
            {0, 0, 0, 10, 2, 3, 4, 11};

    public final static int[] TRUMP_VALUES = {0, 0, 14, 10, 20, 3, 4, 11};

    public final static int CLUBS = 0;
    public final static int SPADES = 4;

    public final static int SEVEN = 0;
    public final static int EIGHT = 1;
    public final static int NINE = 2;
    public final static int TEN = 3;
    public final static int JACK = 4;
    public final static int QUEEN = 5;
    public final static int KING = 6;
    public final static int ACE = 7;
}

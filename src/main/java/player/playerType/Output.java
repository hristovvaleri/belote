package player.playerType;

/**
 * This class represents output for belote game
 * Created by valeri on 25.8.2017 г..
 */
public final class Output {

    private final static String EMPTY_STRING = "";

    /**
     *
     * @param message should not be null or empty string
     */
    public static void display(String message) {
        assert message != null;
        assert !message.matches(EMPTY_STRING);

        System.out.println(message);
    }

}

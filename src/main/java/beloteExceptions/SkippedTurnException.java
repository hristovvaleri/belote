package beloteExceptions;

/**
 * This exception represents when someone skips turn of playing
 * card in belote game
 * Created by valeri on 6.8.2017 г..
 */
public class SkippedTurnException extends RuntimeException {
    public SkippedTurnException(String message) {
        super(message);
    }
}

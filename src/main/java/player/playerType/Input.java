package player.playerType;
import java.util.Scanner;

/**
 * This class represents human player input for playing belote game
 * Created by valeri on 8.8.2017 г..
 */
public final class Input {

    private static final Scanner input = new Scanner(System.in);

    /**
     * @return int choosen input by the player
     */
    public static int getInput() {

        return input.nextInt();
    }


}

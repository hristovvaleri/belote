package declaration;

import beloteExceptions.SkippedTurnException;
import card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents all belote declaration with power and points
 * Created by valeri on 4.8.2017 г..
 */
public enum Declaration {
    TWOHUNDREDS(6, 200),
    HUNDRED_AND_HALF(5, 150),
    HUNDREDS_CARRE(4, 100),
    HUNDREDS_SEQUENCE(3, 100),
    FIFTIES(2, 50),
    TIERCE(1, 30),
    BELOTE(0, 20),
    NONE(-1, 0);

    private final int declarationPower;
    private final int declarationPoints;

    private final static int MAX_POWER = 6;
    private final static int MIN_POINTS = 20;
    private final static int MAX_POINTS = 200;
    private final static int MAX_HAND_SIZE = 8;
    public final static Declaration[] ALL_DECLARATIONS = new Declaration[]{NONE, BELOTE, TIERCE, FIFTIES, HUNDREDS_SEQUENCE
            , HUNDREDS_CARRE, HUNDRED_AND_HALF, TWOHUNDREDS};

    /**
     * @param declarationPower  should be greater than -1 and smalled than 7
     * @param declarationPoints should be greater than 19 and smaller than 201
     */
    Declaration(int declarationPower, int declarationPoints) {
        assert declarationPower >= 0;
        assert declarationPower <= MAX_POWER;
        assert declarationPoints >= MIN_POINTS;
        assert declarationPoints <= MAX_POINTS;

        this.declarationPower = declarationPower;
        this.declarationPoints = declarationPoints;
    }

    /**
     * @return the declaration power
     */
    public int getDeclarationPower() {
        return declarationPower;
    }

    /**
     * @return the declaration points
     */
    public int getDeclarationPoints() {
        return declarationPoints;
    }

    /**
     * @param hand        should not be null
     * @param winningCard should not be null
     * @param playedCard  should not be null
     * @return all the declaration said by the current player
     */
    public static List<Declaration> getDeclaration(List<Card> hand,
                                                   Card winningCard, Card playedCard) {
        assert hand != null;

        List<Declaration> declarations = new ArrayList<>();
        List<Declaration> currentDeclaration;
        if (hand.size() == MAX_HAND_SIZE) {
            declarations.addAll(getDeclarations(hand));
        } else {
            if (playedCard == null || winningCard == null) {
                throw new SkippedTurnException("Someone skipped turn to play a card.The game is aboard!");
            }
            if (Belote.check(hand, winningCard, playedCard)) {
                declarations.add(Declaration.BELOTE);
            }
        }

        return declarations;
    }

    /**
     * @param firstTeam  should not be null
     * @param secondTeam should not be null
     * @return get team which has the higher declaration
     */
    public static int getTeamWithHigherDeclaration(List<Declaration> firstTeam, List<Declaration> secondTeam) {
        assert firstTeam != null;
        assert secondTeam != null;

        int teamOne = getDeclarationPower(firstTeam);
        int teamTwo = getDeclarationPower(secondTeam);

        return teamOne - teamTwo;
    }

    private static List<Declaration> getDeclarations(List<Card> hand) {
        assert hand != null;

        List<Declaration> declarations = new ArrayList<>();
        List<Declaration> currentDeclaration;
        if (!(currentDeclaration = Carre.get(hand)).contains(null)) {
            declarations.addAll(currentDeclaration);
        }
        if (!(currentDeclaration = Carre.get(hand)).contains(null)) {
            declarations.addAll(currentDeclaration);
        }

        return declarations;
    }

    private static int getDeclarationPower(List<Declaration> declarations) {
        assert declarations != null;

        int power = 0;
        for (Declaration currentDeclaration : declarations) {
            if (currentDeclaration.getDeclarationPower() > power) {
                power = currentDeclaration.declarationPower;
            }
        }

        return power;
    }
}

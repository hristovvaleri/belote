package game;

import card.Card;
import declaration.Declaration;
import player.Bid;
import player.BidType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the points of the belote cards and declarations
 * Created by valeri on 5.8.2017 г..
 */
public final class Point {

    private final Bid bid;
    private final List<Card> firstTeam;
    private final List<Card> secondTeam;
    private final int gameWinner;
    private final int counter;
    private final List<Declaration> firstTeamDeclarations;
    private final List<Declaration> secondTeamDeclarations;

    private final static int LAST_TEN = 10;

    /**
     * @param bid                    should not be null
     * @param firstTeam              should not be null
     * @param secondTeam             should not be null
     * @param gameWinner             should be between 0 and 3
     * @param counter                should be 1,2 or 4
     * @param firstTeamDeclarations  should not be null
     * @param secondTeamDeclarations should not be null
     */
    public Point(Bid bid, List<Card> firstTeam, List<Card> secondTeam, int gameWinner, int counter,
                 List<Declaration> firstTeamDeclarations, List<Declaration> secondTeamDeclarations) {
        assert bid != null;
        assert firstTeam != null;
        assert secondTeam != null;
        assert gameWinner > -1 && gameWinner < 4;
        assert counter == 1 || counter == 2 || counter == 4;
        assert firstTeamDeclarations != null;
        assert secondTeamDeclarations != null;

        this.bid = bid;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.gameWinner = gameWinner;
        this.counter = counter;
        this.firstTeamDeclarations = firstTeamDeclarations;
        this.secondTeamDeclarations = secondTeamDeclarations;
    }

    /**
     * @return the teams points for the current game
     */
    public TeamPoints getPoints() {

        int firstTeamPoints = sumPoints(firstTeam) * getBidMultiply();
        int secondTeamPoints = sumPoints(secondTeam) * getBidMultiply();
        int declarationWinningTeam = Declaration.getTeamWithHigherDeclaration(firstTeamDeclarations, secondTeamDeclarations);

        if (declarationWinningTeam > 0) {
            firstTeamPoints += sumDeclarations(firstTeamDeclarations);
        } else if (declarationWinningTeam < 0) {
            secondTeamPoints += sumDeclarations(secondTeamDeclarations);
        }
        if (gameWinner % 2 == 0) {
            firstTeamPoints += LAST_TEN;
        } else {
            secondTeamPoints += LAST_TEN;
        }

        return counter != 1 ? getPointsOnCounterGame(firstTeamPoints, secondTeamPoints, counter) :
                getTeamPoints(firstTeamPoints, secondTeamPoints);
    }


    /**
     * @param points should be grater than -1
     * @return the counted points for the belote game
     */
    public static double calculatePoints(int points) {
        assert points > -1;

        return points / 10;
    }

    private int getBidMultiply() {

        return bid.getBid().equals(BidType.NO_TRUMP) ? 1 : bid.getCounter();
    }

    private int sumPoints(List<Card> team) {
        assert team != null;

        int points = 0;
        for (Card currentCard : team) {
            points += currentCard.getBeloteCard().getWeight().getCardWeight();
        }

        return points;
    }

    private int sumDeclarations(List<Declaration> declarations) {
        assert declarations != null;

        int sum = 0;
        for (Declaration currentDeclaration : declarations) {
            sum += currentDeclaration.getDeclarationPoints();
        }

        return sum;
    }


    private TeamPoints getTeamPoints(int firstTeamPoints, int secondTeamPoints) {
        assert firstTeamPoints > -1;
        assert secondTeamPoints > -1;

        TeamPoints points;

        if (bid.getPlayer().getTeam().getTeam() % 2 == 0) {
            if (firstTeamPoints >= secondTeamPoints) {
                points = new TeamPoints(firstTeamPoints, secondTeamPoints);
            } else {
                points = new TeamPoints(0, firstTeamPoints + secondTeamPoints);
            }
        } else {
            if (firstTeamPoints <= secondTeamPoints) {
                points = new TeamPoints(firstTeamPoints, secondTeamPoints);
            } else {
                points = new TeamPoints(firstTeamPoints + secondTeamPoints, 0);
            }
        }

        return points;
    }


    private TeamPoints getPointsOnCounterGame(int firstTeamPoints, int secondTeamPoints, int counter) {
        assert firstTeamPoints > -1;
        assert secondTeamPoints > -1;
        assert counter == 1 || counter == 2 || counter == 4;

        TeamPoints points;
        if (firstTeamPoints >= secondTeamPoints) {
            points = new TeamPoints((firstTeamPoints + secondTeamPoints) * counter, 0);
        } else {
            points = new TeamPoints(0, (firstTeamPoints + secondTeamPoints) * counter);
        }

        return points;
    }

}

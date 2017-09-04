package player.playerType;

import card.Card;
import player.*;
import declaration.Declaration;

import java.util.List;

/**
 * This class represents the AIPlayer
 * Created by valeri on 4.8.2017 г..
 */
public final class ComputerPlayer extends Player {

    private static final int BID_MIN = 45;
    private static final int MAX_COUNTER = 3;

    /**
     * @param team should not be null
     * @param hand should not be null
     */
    public ComputerPlayer(Team team, List<Card> hand, String name) {
        super(team, hand, name);
    }

    @Override
    public Bid callBid(Bid currentBid) {
        assert currentBid != null;

        int counter = 1;
        Bid bid = new Bid(BidType.PASS, counter, this);
        for (int count = 0; count < BidType.allBids.length; count++) {
            Bid bidCheck = new Bid(BidType.allBids[count], bid.getCounter(), this);
            int currentBidSum = HandType.sumHandPoints(HandType.getBeloteHand(super.getHand(), bidCheck));
            if (currentBidSum > BID_MIN && bidCheck.getBid().getBidPower() > currentBid.getBid().getBidPower()) {

                counter = (BidType.allBids[count].equals(bidCheck) && bid.getCounter() < MAX_COUNTER)
                        ? counter *= 2 : 1;
                bid = new Bid(BidType.allBids[count], counter, this);
                break;
            }
        }

        return bid;
    }

    @Override
    public List<Declaration> callDeclaration(Card winningCard,
                                             Card playedCard) {

        return Declaration.getDeclaration(super.getHand(), winningCard, playedCard);
    }

    @Override
    public Card playCard(Card winningCard, Bid bid) {
        assert bid != null;

        Card playedCard = GameType.getCard(super.getHand(), winningCard, bid);
        super.getHand().remove(playedCard);

        return playedCard;
    }

}

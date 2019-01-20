/* Rhea Kothari
 * rkk2133
 * Defines a Player class
 */

import java.util.ArrayList;
public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
	// you may choose to use more instance variables
		
	public Player(){
        // create a player here
        hand = new ArrayList<Card>();
        bankroll = 20;
	    
	}

	public void addCard(int pos, Card c){
        hand.add(pos, c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
	    hand.remove(c);
    }
		
    public void bets(double amt){
        // player makes a bet
        bet = amt;
    }

    public void winnings(double odds){
        //multiply the odds, which in game class is the payout,
        //by the bet to get total winnings and add to bankroll
        double winnings = odds*bet;
        bankroll = bankroll + winnings;
    }

    public double getBankroll(){
        return bankroll;
    }

    // you may wish to use more methods here
    public ArrayList<Card> getHand(){
        //used to access the hand from other classes
        return hand;
    }
    
    public void clearHand(){
        //to remove all cards if want to bet again
        hand.clear();
    }
    
    public void print(){
        //to print out all final information after replacements are made
        System.out.println("bet: " + bet);
        System.out.println("bankroll: " + bankroll);
        System.out.println("hand: " + this.getHand());

    }
}



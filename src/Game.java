/* Rhea Kothari
 * rkk2133
 * Defines a Game class
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Game {
	
	private Player p;
	private Deck cards;
    private ArrayList<Card> testHand;
    private int threeKindRemover; // integer to remove the three 
                                  // of a kind cards and test for 
                                  // a one pair in isFullHouse()
	

	public Game(String[] testHandStr){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush 
        p = new Player();
        cards = new Deck();
        threeKindRemover = 0;
        testHand = new ArrayList<Card>();
        for (String cardStr : testHandStr){
            String suitStr = cardStr.substring(0,1);
            String rankStr = cardStr.substring(1,cardStr.length());
            int suit;
            if (suitStr.equals("s")) {
                suit = 1;
            }
            else if (suitStr.equals("h")) {
                suit = 2;
            }
            else if (suitStr.equals("d")) {
                suit = 3;
            }
            else {
                suit = 4;
            }
            Card card = new Card (suit, Integer.parseInt(rankStr));
            System.out.println("card " + cardStr + " -> " 
                               + card.toString());  
            testHand.add(card);
            Collections.sort(testHand);
        }
	}
	
    
	public Game(){
		// This no-argument constructor is to actually play a normal game
        p = new Player();
        cards = new Deck();
        threeKindRemover = 0;
        testHand = new ArrayList<Card>();
	}
	
    
	public void play(){
		// this method should play the game	   
        String score;
		if (testHand.size() > 0){
            score = this.checkHand(testHand);
            System.out.println ("Score: " + score);
        }
        else{
            while (p.getBankroll() > 0){
                System.out.println(
                    "How many tokens (1-5) would you like to bet? (Current bankroll: "
                    + p.getBankroll() + ")"
                );
                Scanner input = new Scanner(System.in);
                p.bets(input.nextDouble());
                this.createHand();
                if (!this.changeHand()){
                    continue;
                }
                score = this.checkHand(p.getHand());
                double payout = this.getPayout(score);
                p.winnings(payout);
                System.out.println("score: " + score);
                System.out.println("payout: " + payout);
                System.out.println("bankroll: " + p.getBankroll());
            }
            System.out.println("Game Over: your money has run out!");
        }
    }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		Collections.sort(hand);
		if (isRoyalFlush(hand)){
            return "Royal Flush";
        }
        else if(isStraightFlush(hand)){
            return "Straight Flush";
        }
        else if(isFourOfKind(hand)){
            return "Four of a Kind";
        }
        else if(isFullHouse(hand)){
            return "Full House";
        }
        else if(isFlush(hand)){
            return "Flush";
        }
        else if(isStraight(hand)){
            return "Straight";
        }
        else if(isThreeOfKind(hand)){
            return "Three of a Kind";
        }
        else if(isTwoPair(hand)){
            return "Two Pair";
        }
        else if (isOnePair(hand)){
            return "One Pair";
        }
        else{
            return "None";
        }   
	}
    
  
    public int getPayout(String handScore){
        //converting the score to the corresponding payout
        if (handScore.equals("Royal Flush")){
            return 250;
        }
        if(handScore.equals("Straight Flush")){
            return 50;
        }
        if(handScore.equals("Four of a Kind")){
            return 25;
        }
        if(handScore.equals("Full House")){
            return 6;
        }
        if(handScore.equals("Flush")){
            return 5;
        }
        if(handScore.equals("Straight")){
            return 4;
        }
        if(handScore.equals("Three of a Kind")){
            return 3;
        }
        if(handScore.equals("Two Pair")){
            return 2;
        }
        if (handScore.equals("One Pair")){
            return 1;
        }
        else {
            return -5;
        }
    }

    
    public void createHand(){
        //creating the initial hand given to the player
        p.clearHand();
        cards.shuffle();
        threeKindRemover = 0;
        for (int i = 0; i < 5; i++){
             p.addCard(i, cards.deal());
        }
        p.print();
    }
    
    
    public boolean changeHand(){
        // getting the new hand after the player inputs which/how many cards to replace
        Scanner handInp = new Scanner(System.in);
        System.out.println(
            "Please enter integer position of cards you would like to remove," +
            " separated by commas (e.g. '1,4,5' for first, fourth, and fifth cards)." +
            " If wish to keep all cards, enter 'none'."
        );
        String line = handInp.nextLine();
        if (!line.equals("none")){
            // Finding cards to be replaced
            String[] stringPos = line.split(",");
            int[] intPos = new int[stringPos.length];
            for (int j = 0; j < stringPos.length; j++){
                String numAsString = stringPos[j];
                int position = Integer.parseInt(numAsString);
                if ((position < 1) || (position > 5)){
                    System.out.println("Error: invalid position: " + position);
                    System.out.println("Position must be from 1 to 5");
                    return false;
                }
                intPos[j] = position;
            }
            
            // Change cards in hand based on entered position 
            for (int a = 0; a < intPos.length; a++){
                p.removeCard(p.getHand().get(intPos[a] - 1));
                p.addCard((intPos[a] - 1), cards.deal());
            }
        }
        System.out.println(p.getHand());
        return true;
    }
    
    
    //Following methods each test for a specific score for the hand
    public boolean isOnePair(ArrayList<Card> myHand){
        boolean found = false;
		for (int i = 0; i < myHand.size(); i++){
            for (int j=i+1;(j < myHand.size()) && (!found); j++){
                if (myHand.get(i).getRank() == myHand.get(j).getRank()){
                    found = true;
                }
            }
            if (found){
                break;
            }            
        }
        return found;
   }
    
    
    public boolean isTwoPair(ArrayList<Card> hand){
        // removing cards below to check for 2 pairs
        // - so must copy arrayList
        ArrayList<Card> myHand = new ArrayList<>(hand);
        boolean found = false;
		for (int i = 0; i < myHand.size(); i++){
            if (found == true){
                break;
            }
            for (int j=i+1;(j < myHand.size()) && (!found); j++){
                if (i != j){
                    if (myHand.get(i).getRank() == myHand.get(j).getRank()){
                        // remove current pair and check myHand again for 
                        // another pair
                        myHand.remove(myHand.get(i));
                        myHand.remove(myHand.get(i));
                        isOnePair(myHand); 
                        if (isOnePair(myHand)){
                            found = true;
                        }
                    }
                }
            }
        }
        return found;
    }
    
    
    public boolean isThreeOfKind(ArrayList<Card> myHand){
        boolean found = false;
		for (int i = 0; i < myHand.size(); i++){
            for (int j = i+1; j < myHand.size(); j++){
                for (int k=j+1;(k < myHand.size()) && (!found); k++){
                    if ((i != j) && (j != k) && (i != k)){
                        if (myHand.get(i).getRank() == myHand.get(j).getRank()){
                            if (myHand.get(j).getRank() == myHand.get(k).getRank()){
                                // need to access the rank of the 3OfKind in isFullHouse()
                                // so don't have to loop through hand again
                                threeKindRemover = myHand.get(i).getRank();
                                found = true;
                            }
                        }
                    }
                }
                if (found){
                    break;
                }
            }
           if (found){
               break;
            }
        }
        return found;
    }
    
    public boolean isStraight(ArrayList<Card> myHand){
        boolean found = false;  
        int first = myHand.get(0).getRank();
        int last = myHand.get(4).getRank();
        if ((last - first) == 4){
            found = true;
        }
        //Special condition to account for a low ace
        else if (first == 2){
            found = true;
            for (int i = 0; i < myHand.size(); i++){
                int j = i+1;
                if (i == 3){
                    if ((myHand.get(i).getRank() - 4) == myHand.get(j).getRank()){
                        break;
                    }
                }
                if ((myHand.get(i).getRank() + 1) != myHand.get(j).getRank()){
                    found = false;
                    break;
                }
            }
        }        
        
        //Special condition to account for a high ace
        else if (first == 10){
            found = true;
            for (int i = 0; i < myHand.size(); i++){
                int j = i+1;
                if (i == 3){
                    if ((myHand.get(i).getRank() - 12) == myHand.get(j).getRank()){
                        break;
                    }
                }
                if ((myHand.get(i).getRank() + 1) != myHand.get(j).getRank()){
                    found = false;
                    break;
                }
            }
        }
        return found;
    }
    
    
	public boolean isFlush(ArrayList<Card> myHand){
        return (
            (myHand.get(0).getSuit() == myHand.get(1).getSuit()) &&
            (myHand.get(1).getSuit() == myHand.get(2).getSuit()) &&
            (myHand.get(2).getSuit() == myHand.get(3).getSuit()) &&
            (myHand.get(3).getSuit() == myHand.get(4).getSuit())
        );
    }
    
    
    public boolean isFullHouse(ArrayList<Card> hand){
        ArrayList<Card> myHand = new ArrayList<>(hand);
        boolean found = false;
        if (isThreeOfKind(myHand)){
            int i = 0;
            while (i < myHand.size()){
                // threeKindRemover saved in isThreeOfKind()
                // so don't have to loop through again here
                if (myHand.get(i).getRank() == threeKindRemover){
                    myHand.remove(myHand.get(i));
                }
                else{
                    i++;
                }
            }
            if (isOnePair(myHand)){
                found = true;
            }
        }
        return found;
    }
    

    public boolean isFourOfKind(ArrayList<Card> myHand){
       boolean found = false;
       for (int i = 0; (i < myHand.size()) && (!found); i++){
           for (int j = i+1; (j < myHand.size()) && (!found); j++){
               for (int k = j+1; (k < myHand.size()) && (!found); k++){
                   for (int h = k+1; (h < myHand.size()) && (!found); h++){
                       if ((myHand.get(i).getRank() == myHand.get(j).getRank())&&                           
                           (myHand.get(j).getRank() == myHand.get(k).getRank())&&
                           (myHand.get(k).getRank() == myHand.get(h).getRank())){
                               found = true;
                       }
                   }
               }
           }
       }
       return found;
    }
    
    
    public boolean isStraightFlush(ArrayList<Card> myHand){
        return (isStraight(myHand) && isFlush(myHand));
    }
    
    public boolean isRoyalFlush(ArrayList<Card> myHand){
        if (isStraightFlush(myHand)){
            if (myHand.get(0).getRank() == 10){
                return true;
            }
        }
        return false;
    }
    
    
    
}

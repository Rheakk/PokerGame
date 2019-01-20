/* Rhea Kothari
 * rkk2133
 * Defines a Deck class - holds the 52 cards
 */
import java.util.*;

public class Deck {
	
	private Card[] cards;
    private Card card;
	private int top; // the index of the top of the deck
    private boolean isTesting;

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
        cards = new Card[52];
		for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                cards[i*13+j] = new Card(i+1, j+1);
            }
        }
        top = 0;
        isTesting = false;
        printDeck();
	}
	
	public void shuffle(){
		// shuffle the deck here
        for (int j = 0; j < 10000; j++){
            int indexA = (int) (Math.random() * 52.0);
            int indexB = (int) (Math.random() * 52.0);
            Card temp = cards[indexA];
            cards[indexA] = cards[indexB];
            cards[indexB] = temp;
        }
        top = 0;
        printDeck();
	}
    
        
	public Card deal(){
		// deal the top card in the deck
        Card topCard = cards[top];
        if(isTesting){
            System.out.println("Shown card from top of deck: " + 
                               topCard.toString());
        }
        top++;
        return topCard;
    }
	
	// add more methods here if needed
        

    public void printDeck(){
        //printDeck method - to print the deck
        if (!isTesting) {
            return;
        }
        System.out.println ("Deck------");
        for (Card card : cards) {
            System.out.println (card.toString());
        }
        System.out.println ("------End");
    }
}

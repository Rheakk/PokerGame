/* Rhea Kothari
 * rkk2133
 * Defines a Card class which has a suit and rank
 */

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	private String[] suitNames = {"Spade", "Heart", "Diamond", "Club"};
    
	public Card(int s, int r){
		//make a card with suit s and value v
		suit = s;
        rank = r;
	}
	
    
	public int compareTo(Card c){
        // use this method to compare cards so they 
		// may be easily sorted
        if (rank == 1){
            if (c.rank == 1){
                return 0;
            }
            return 1;
        }
        if (c.rank == 1){
            return -1;
        }
        return Integer.compare(rank, c.rank);
	}
	
    
	public String toString(){
		// use this method to easily print a Card object
		
		return suitNames[suit-1] + " " + rank;
	}
    
	// add some more methods here if needed
        public int getRank(){
        //to access rank from other classes
        return rank;
    }
    
    public int getSuit(){
        //to access suit from other classes
        return suit;
    }

}

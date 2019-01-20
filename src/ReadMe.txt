**********************************************
Programming Project 4: POKER GAME PROJECT 
Name: Rhea Kothari
UNI: rkk2133
Due Date: 11/2/18
**********************************************

Card Class:
    1. added instance variable: an array of all the suit names, to be easily accessed in toString().
    Integer to suit encoding is: 1-Spade, 2-Heart, 3-Diamond, 4-Club.
    2. compareTo(): added if statements in beginning to account for if the card is an Ace,
    rank = 1 but still is the highest card. Therefore Ace is always the last card of hand. 


Deck Class:
    1. added instance variable: created a boolean variable isTesting - printDeck() is called if 
    isTesting is true
    2. Deck constructor: loops through suits and then classes to create 52 card deck
    3. shuffle(): randomly gets 2 integers from 1 to 52, swaps cards using these two integers as ranks
    4. printDeck(): prints the deck for testing purposes
    
Game Class:
    1. testHand constructor: iterated through the array argument and split each string element into suit
    and rank based on index value to decode the card. Then created new card out of info and added to hand.
    2. play(): if testHand is passed, then only checks the hand and gives back the score. If regular game:
        1. will keep playing the game until the player runs out of money. I give a starting bankroll of 20,
        and make the payout for no score = -5. 
        2. asks for bet
        3. stores the bet value
        4. createHand() - draws a hand and gives it to the player
        5. changeHand() - asks for replacement and modifies hand accordingly
        6. gets the score of hand and stores it in variable score
    payoff calculated and added to bankroll in both cases. 
    3. checkHand() - if statements checking for each score and returns the String value of the score
    4. getPayoff() - takes the String score and returns the corresponding payoff
    5. createHand() - to set up initial hand – shuffles the deck, deals 5 cards and adds them to hand
    6. changeHand() - asks user to input position of cards that they want to replace separated by only a
    comma - splits the string and stores into an array, then iterates through the resulting integer array
    to access card based on position, remove card, and add a replacement card
    7. isOnePair() - makes comparison between 2 cards in entire list until finds a match
    8. isTwoPair() - finds one pair, then removes both cards of pair and if finds another one returns true
    9. isThreeOfKind() - makes comparison between 3 cards in entire list until finds a match
    10. isStraight() - if difference between first and last card = 4 returns true
    11. isFlush() - compares integer value of suit of all cards in hand and returns true if equal
    12. isFullHouse() - first looks for 3 of kind - if finds it, removes those cards from copied arrayList
    and looks for one pair. Returns true if both are found.
    13. isFourOfKind() - compares 4 different cards until finds a match
    14. isStraightFlush() - calls isStraight and isFlush - both must be true
    15. isRoyalFlush() - calls isStraightFlush and checks if beginning rank is a 10 to make sure has royals
     (hand would already be sorted).
    
Player Class:
    1. initialized hand, set bankroll = 20
    2. added getHand() to access hand from other classes
    3. added clearHand() to clear hand if want to play again
    4. added print() to print out all information after hand is scored and payoff added to bankroll
package com.example.solitaire;

public class Card {

    private final static int SPADES = 0;   // Codes for the 4 suits, plus Joker.
    private final static int HEARTS = 1;
    private final static int DIAMONDS = 2;
    private final static int CLUBS = 3;
    private final static int JOKER = 4;

    public final static int ACE = 1;      // Codes for the non-numeric cards.
    public final static int JACK = 11;    //   Cards 2 through 10 have their
    public final static int QUEEN = 12;   //   numerical values for their codes.
    public final static int KING = 13;

    private boolean inStack = false;
    private boolean isFaceUp = false;
    private boolean canMove = false;

    private String img = "";

    /**
     * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
     * CLUBS, or JOKER.  The suit cannot be changed after the card is
     * constructed.
     */
    private final int suit;

    /**
     * The card's value.  For a normal card, this is one of the values
     * 1 through 13, with 1 representing ACE.  For a JOKER, the value
     * can be anything.  The value cannot be changed after the card
     * is constructed.
     */
    private final int value;

    /**
     * Creates a Joker, with 1 as the associated value.  (Note that
     * "new Card()" is equivalent to "new Card(0, Card.JOKER)".)
     */
    public Card() {
        suit = JOKER;
        value = 0;
    }

    /**
     * Creates a card with a specified suit and value.
     **/
    public Card(int val, int suit) {
        if (suit != SPADES && suit != HEARTS && suit != DIAMONDS &&
                suit != CLUBS && suit != JOKER)
            throw new IllegalArgumentException("Illegal playing card suit");
        if (suit != JOKER && (val < 1 || val > 13))
            throw new IllegalArgumentException("Illegal playing card value");
        value = val;
        this.suit = suit;
    }

    /**
     * Returns the suit of this card.
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Returns the value of this card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a String representation of the card's suit.
     */
    public String getSuitAsString() {
        switch (suit) {
            case SPADES:
                return "Spades";
            case HEARTS:
                return "Hearts";
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            default:
                return "Joker";
        }
    }

    /**
     * Returns a String representation of the card's value.
     */
    public String getValueAsString() {
        if (suit == JOKER)
            return "" + value;
        else {
            switch (value) {
                case 1:
                    return "Ace";
                case 2:
                    return "2";
                case 3:
                    return "3";
                case 4:
                    return "4";
                case 5:
                    return "5";
                case 6:
                    return "6";
                case 7:
                    return "7";
                case 8:
                    return "8";
                case 9:
                    return "9";
                case 10:
                    return "10";
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                default:
                    return "King";
            }
        }
    }

    /**
     * Returns a string representation of this card, including both
     * its suit and its value
     */
    public String toString() {
        if (suit == JOKER) {
            if (value == 0)
                return "Joker";
            else
                return "Joker #" + value;
        } else
            return getValueAsString() + " of " + getSuitAsString();
    }

    public boolean CheckIfMovable() {
        return canMove;
    }

    public boolean CheckIsFlipped(){
        return isFaceUp;
    }

    public boolean CheckInStack(){
        return inStack;
    }

    public void putInStack(){
        inStack = true;
    }

    public void removeFromStack(){
        inStack = false;
    }

    public void dropInFinalLocation(){
        inStack = true;
        canMove = false;
    }

    public void flipCard(){
        inStack = false;
        canMove = true;
        isFaceUp = true;
    }



}
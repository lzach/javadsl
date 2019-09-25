package patience;

public class Card {
    public final int value;
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    public final Suit suit;
    public  enum Colour {
        RED, BLACK
    }
    public final Colour colour;
    public boolean faceup;

    public Card (int n, Suit s, boolean fUp) {
        this.value = n;
        this.suit = s;
        faceup = fUp;
        this.colour = suit == Suit.HEARTS ? Colour.RED : suit == Suit.DIAMONDS ? Colour.RED : Colour.BLACK;
    }
    public Card (int n, Suit s) {
        this(n, s, false);
    }

    public Card ()  {
        this(2, Suit.HEARTS);
    }

    public int value() {
        return value;
    }

    public Suit suit() {
        return suit;
    }
    public Colour color() {
        return colour;
    }

    public boolean equals(Object o) {
        return o instanceof Card && ((Card) o).id() == id();
    }

    public int id() {
        return suit.ordinal()*13 + value - 1;
    }

    public void setFaceUp(boolean fUp)
    {
        faceup = fUp;
    }

    public boolean faceUp()
    {
        return faceup;
    }
    
    public int hashCode() {
        return id();
    }
    
    public String toString() {
      String[] suits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
      return "Card(" + value +", " + suits[suit.ordinal()] + ")";
    }
}

package patience;

import java.util.ArrayList;
import java.util.Collections;


public class Deck extends ArrayList<Card> {


    public Deck() {
      this(1);
    }
    
    public Deck(int size) {
      for ( int n = 0; n < size; ++n ) {
        for (int i = 0; i < 52; i++)
        {
            int v = i % 13 + 1;
            int c = i / 13;
            add(new Card(v, Card.Suit.values()[c]));
        }
      }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card deal() {
        int lastIndex = size() - 1;

        Card returnCard = get(lastIndex);
        remove(lastIndex);
        return returnCard;
    }

    public void putTop(Card c) {
      c.faceup = false;
      if ( !contains(c) ) add(c);
    }
    
    public void putBottom(Card c) {
      c.faceup = false;
      if ( !contains(c) ) add(0, c);
    }

    public void putTop(Pile p) {
      Collections.reverse(p);
      for ( Card c : p ) c.faceup = false;
      addAll(p);
      p.clear();
    }
    public void putBottom(Pile p) {
      Collections.reverse(p);
      for ( Card c : p ) c.faceup = false;
      addAll(0, p);
      p.clear();
    }
    
    public String toString() {
      return "Deck(" + size() + ")";
    }
}

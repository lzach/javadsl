package patience;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludvig on 09/01/15.
 */
public class Pile extends ArrayList<Card> {
   // List<Card> cards = new ArrayList<Card>();

    public Pile(){}
    
    protected Pile(List<Card> cards) {
        addAll(cards);
    }
//     boolean contains(Card card) {
//         return contains(card);
//     }
    public Card bottom() {
        if ( isEmpty() ) return null;
        return get(0);
    }

    public Card top() {
        if ( isEmpty() ) return null;
        return get(size() - 1);
    }

    public Pile top(int n) {
        if ( isEmpty() ) return null;
        return new Pile(subList(size() - 1 - n, size() - 1));
    }
    
    public boolean equals(Object p) {
      return this == p;
    }
    
    public String toString() {
      return "Pile(" +size() + ")";
    }

}

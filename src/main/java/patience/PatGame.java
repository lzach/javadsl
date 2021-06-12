package patience;

/**
 * Created by ludvig on 24/06/15.
 */
public abstract class PatGame {
    Deck deck;
    public PatGame() {
      deck = new Deck();
    }
    
    abstract public boolean canMove(Card card, Pile pile);
    abstract public boolean canMovePile(Pile from, Pile to);
    abstract public void deal(Deck deck);
    abstract public void setup(Deck deck);
    abstract public boolean hasWon();
    abstract public boolean hasLost(Deck deck);
    abstract public boolean shouldTurn(Pile pile, Card card);
    abstract public void redeal(Deck deck);
    abstract public PileGroup getPileGroup(String name);


    public void move(Card card, Pile from, Pile to) {
      if ( from.equals(to) ) return ;
      if ( canMove(card, to) ) {
        from.remove(card);
        to.add(card);
      }
    }
    
    public void move(Pile origin, Pile from, Pile to) {
      if ( from.equals(origin) ) return ;
      System.out.println(from);
        System.out.println(from.bottom() + ", " + to.top());
      if ( canMovePile(from, to) ) {
        System.out.println("canMovePile!");
        to.addAll(from);
        origin.removeAll(from);
      }
    }
    
    public void deal() {
      if ( !deck.isEmpty() ) deal(deck);
    }
    
    public void setup() {
      setup(deck);
    }
}

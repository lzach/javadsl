package patience;

/**
 * Created by ludvig on 24/06/15.
 */
public class KlondikeGame extends PatGame {
    PileGroup foundation = new PileGroup(4);
    PileGroup waste = new PileGroup(1);
    PileGroup tableau = new PileGroup(7);
    @Override
    public boolean canMove(Card card, Pile pile) {
      return foundation.contains(pile) && pile.isEmpty() && card.value == 1 ||
            tableau.contains(pile) && pile.isEmpty() && card.value == 13 ||
            !pile.isEmpty() && foundation.contains(pile) && card.value == pile.top().value + 1 && card.suit == pile.top().suit ||
        !pile.isEmpty() && tableau.contains(pile) && card.value == pile.top().value - 1 && card.colour != pile.top().colour;
    }

    @Override
    public boolean canMovePile(Pile from, Pile to) {
      for ( int i = 1; i < from.size(); ++i ) {
       if ( from.get(i).value != from.get(i-1).value - 1 || from.get(i).colour == from.get(i-1).colour ) return false;
      }
      return tableau.contains(to) && (to.isEmpty() && from.top().value == 13 || !to.isEmpty() && from.bottom().value == to.top().value - 1 && from.bottom().colour != to.top().colour);
    }

    @Override
    public void deal(Deck deck) {
        waste.deal(deck.deal());
    }

    @Override
    public void setup(Deck deck) {
        for ( int i = 0; i < tableau.size(); ++i ) {
            for ( int j = i; j < tableau.size(); ++j)
                tableau.get(j).add(deck.deal());
        }
    }

    @Override
    public boolean hasWon() {
        for ( Pile p : foundation ) if ( p.isEmpty() ) return false;
        return foundation.get(0).top().value == 13 && foundation.get(1).top().value == 13 &&
                foundation.get(2).top().value == 13 && foundation.get(3).top().value == 13 ;
    }

    @Override
    public PileGroup getPileGroup(String name) {
        if ( name.equals("tableau") ) return tableau;
        if ( name.equals("waste") ) return waste;
        if ( name.equals("foundation") ) return foundation;
        return null;
    }
    
    public void redeal(Deck deck) {
      deck.putTop(waste.get(0));
    }

    public boolean shouldTurn(Pile pile, Card card) {
      return waste.contains(pile) || pile.top().equals(card);
    }
    
    @Override
    public boolean hasLost(Deck deck) {
      return deck.isEmpty();
    }
}

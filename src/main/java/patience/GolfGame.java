package patience;

/**
 * Created by ludvig on 08/09/15.
 */
public class GolfGame extends PatGame {
    PileGroup foundation = new PileGroup(1);
    PileGroup tableau = new PileGroup(7);

    @Override
    public boolean canMove(Card card, Pile pile) {
      //return true;
      return foundation.contains(pile) && !(pile.top().value == 13) && (card.value == pile.top().value - 1 || card.value == pile.top().value + 1); 
    }
    @Override
    public boolean canMovePile(Pile from, Pile to) {
      return false;
    }

    @Override
    public void deal(Deck deck) {
        foundation.deal(deck.deal());
    }

    @Override
    public void setup(Deck deck) {
        for ( int i = 0; i < 5; ++i ) {
            for ( int j = 0; j < tableau.size(); ++j)
                tableau.get(j).add(deck.deal());
        }
    }

    @Override
    public boolean hasWon() {
        for ( Pile pile : tableau ) {
          if ( !pile.isEmpty() ) return false;
        }
        return true;
    }

    @Override
    public PileGroup getPileGroup(String name) {
        if ( name.equals("tableau") ) return tableau;
        if ( name.equals("foundation") ) return foundation;
        return null;
    }

    public boolean shouldTurn(Pile pile, Card card) {
      return true ;
    }
    
    public void redeal(Deck deck) {
    }
    
    @Override
    public boolean hasLost(Deck deck) {
      return deck.isEmpty();
    }
}

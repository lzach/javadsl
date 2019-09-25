package patience;

import java.util.ArrayList;

/**
 * Created by ludvig on 01/03/15.
 */
public class Golf extends Game {
    private Deck deck = new Deck();
    private Pile foundation = new Pile();
    private ArrayList<Pile> tableau = new ArrayList<Pile>();

    public Golf() {
        for (int i = 0; i < 7; ++i) {
            Pile pile = new Pile();
            for (int j = 0; j < 5; ++j) {
              pile.add(deck.deal());
            }
            tableau.add(pile);
        }
        foundation.add(deck.deal());

    }

    @Override
    public void deal() {
        foundation.add(deck.deal());
    }

    @Override
    protected boolean canMove(Card card, Pile pile1, Pile pile2) {
        if ( pile2.equals(foundation) ) {
            if ( card.value == 14 && pile2.top().value == 2 || card.value == 2 && pile2.top().value == 14 ) return true;
            if ( pile2.top().value == 13 ) return false;
            if (pile2.top().value == card.value -1 || pile2.top().value == card.value + 1) return true;
        }
        return false;
    }

    @Override
    protected boolean canMove(Pile mpile, Pile pile1, Pile pile2) {
        return false;
    }
}

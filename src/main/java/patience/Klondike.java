package patience;

import java.util.ArrayList;


public class Klondike extends Game {
    private Deck deck = new Deck();
    private ArrayList<Pile> up = new ArrayList<Pile>();
    private ArrayList<Pile> down = new ArrayList<Pile>();
    private ArrayList<Pile> dealt = new ArrayList<Pile>();


    public Klondike() {
        up.add(new Pile());
        up.add(new Pile());
        up.add(new Pile());
        up.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        down.add(new Pile());
        dealt.add(new Pile());

        for (int i = 1; i <= down.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                down.get(i).add(deck.deal());
            }
        }
    }

    protected boolean canMove(Card card, Pile pile1, Pile pile2) {
        if ( up.contains(pile2) ) {
            if ( pile2.size() == 0 && card.value == 14 ) return true;
            else if ( pile2.top().suit.equals(card.suit) && pile2.top().value == card.value - 1 ) return true;
        } else if ( down.contains(pile2) ) {
            if (pile1.size() == 0 && card.value == 13 ) return true;
            else if ( pile2.top().value == card.value - 1 && ! pile2.top().colour.equals(card.colour)) {
                return true;
            }
        }
        return false;
    }

    protected boolean canMove(Pile mpile, Pile pile1, Pile pile2) {
        if (mpile.isEmpty() || !mpile.bottom().faceUp()) return false;

        if ( mpile.size() == 1 ) return canMove(mpile.top(), pile1, pile2);

        for ( int i = 1; i < mpile.size(); ++i ) {
            if ( mpile.get(i - 1).value != mpile.get(i).value - 1 || mpile.get(i - 1).suit.equals(mpile.get(i).suit) )
                return false;
        }
        return mpile.bottom().value == pile2.top().value - 1 || !(mpile.bottom().suit.equals(pile2.top().suit));
    }

    public void deal() {
        if ( deck.isEmpty() ) {
            deck.addAll(dealt.get(0));
            dealt.get(0).clear();
        }
        dealt.get(0).add(deck.deal());
        dealt.get(0).add(deck.deal());
        dealt.get(0).add(deck.deal());
    }
}

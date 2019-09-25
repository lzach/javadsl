package patience;

import java.util.List;

public abstract class Game {

    public abstract void deal();
    protected abstract boolean canMove(Card card, Pile pile1, Pile pile2);
    protected abstract boolean canMove(Pile mpile, Pile pile1, Pile pile2);

    public void move(Card card, Pile pile1, Pile pile2) {
        if ( pile1.contains(card) ) {
            if ( canMove(card, pile1, pile2) ) {
                pile1.remove(card);
                pile2.add(card);
            }
        }
    }

    public void move(Pile mpile, Pile pile1, Pile pile2) {
        if ( pile1.subList(pile1.size() - mpile.size(), pile1.size()).equals(mpile) ) {
            if ( canMove(mpile, pile1, pile2) ) {
                List<Card> list = pile1.subList(0, pile1.size() - mpile.size());
                pile1.clear(); // ??? why not assign sublist straight away...
                pile1.addAll(list);
                pile2.addAll(mpile);
            }
        }
    }
}

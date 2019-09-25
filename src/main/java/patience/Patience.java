package patience;

public class Patience {

    public static MoveAction getMove(Game game) {
        // stub, should get the action from user input somehow.
        return new MoveAction(new Card(), new Pile(), new Pile());
    }

    public static void main(String[] args) {
        Game game = new Golf();

        while (true) {
            MoveAction action = getMove(game);
            switch (action.action) {
                case DEAL:
                    game.deal();
                    break;
                case MOVE_CARD:
                    game.move(action.card, action.from, action.to);
                    break;
                case MOVE_PILE:
                    game.move(action.mpile, action.from, action.to);
                    break;
                case NONE:
                    break;
            }
        }

    }
}

class MoveAction {
    Card card;
    Pile from;
    Pile to;
    Pile mpile;

    enum ActionType {
        DEAL,
        MOVE_CARD,
        MOVE_PILE,
        NONE
    }
    ActionType action = ActionType.NONE;

    public MoveAction(Card card, Pile from, Pile to) {
        this.card = card;
        this.from = from;
        this.to = to;
        this.action = ActionType.MOVE_CARD;
    }
    public MoveAction(Pile mpile, Pile from, Pile to) {
        this.mpile = mpile;
        this.from = from;
        this.to = to;
        this.action = ActionType.MOVE_PILE;
    }
    public MoveAction() {
        this.action = ActionType.DEAL;
    }
}


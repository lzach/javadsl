package patience;

class Rect {
  int x, y, w, h;
  public Rect(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  public boolean inside(int x, int y) {
    return this.x <= x && this.y <= y && this.x + this.w > x && this.y + this.h > y;
  }
  
  public String toString() {
    return "Rect(" + x + ", " + y + ", " + w + ", " + h + ")";
  }
}

public abstract class PatLayout {
  protected final int CARD_W = 100;
  protected final int CARD_H = 143;
  protected static final int SYMBOL_W = 10;
  protected static final int SYMBOL_H = 5;
  protected static final int TOP = 0;
  protected static final int BOTTOM = 700;
  protected static final int LEFT = 0;
  protected static final int RIGHT = 1000;

  public abstract Rect getBounds(Deck d);
  public abstract Rect getBounds(PileGroup pg);
  public abstract Rect getBounds(Pile p);
  public abstract Rect getBounds(Pile p, int index);
  public abstract void layout();
}

class PolyRect {
  PolyRect []rects;
  Object object;
  int x,y,w,h;

  public PolyRect(PolyRect []rects, Object o) {
    if ( rects.length == 0 ) throw new IllegalArgumentException("PolyRect(PolyRect[], Object) requires at least one sub rectangle");
    x = rects[0].x;
    y = rects[0].y;
    w = rects[0].w;
    h = rects[0].h;
    for ( PolyRect rect : rects ) {
      if ( rect.x < x ) {
        w += x - rect.x;
        x = rect.x;
      }
      if ( rect.x < x ) {
        w += x - rect.x;
        x = rect.x;
      }
      if ( rect.x + rect.w > x + w ) {
        w = (rect.x + rect.w) - x;
      }
      if ( rect.y + rect.h > y + h) {
        h = (rect.y + rect.h) - y;
      }
    }
  }

  public PolyRect(int x, int y, int w, int h, Object o) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.object = o;
  }

  public boolean inside(int x, int y) {
    return this.x <= x && this.y <= y && this.x + this.w > x && this.y + this.h > y;
  }

  public Object at(int x, int y) {
    for ( PolyRect rect : rects ) {
      if ( rect.inside(x, y) ) {
        return rect.at(x, y);
      }
    }
    return object;
  }

  public Object at(int x, int y, Class cls) {
    Object o = null;
    for ( PolyRect rect : rects ) {
      if ( rect.inside(x, y) ) {
        o = rect.at(x, y, cls);
        if ( o != null ) return o;
      }
    }
    if ( cls.isInstance(object) ) return object;
    return null;
  }
}


class KlondikeLayout extends PatLayout{
  private PatGame game;
  private PolyRect [] rects;

  // Get the thingimajigg
  // pilegroup -> rect
  // pile -> rect
  // card -> rect
  // deck -> rect

  public KlondikeLayout(PatGame game){
    this.game = game;
  }

  public Rect getBounds(Deck d) {
    return new Rect(LEFT + 20, TOP + 20, CARD_W, CARD_H);
  }
  
  public Rect getBounds(PileGroup pg) {
    if ( pg == (game.getPileGroup("foundation")) ) return new Rect(RIGHT - 4 *(CARD_W+20) - 20,  TOP + 20, 4*(CARD_W + 20), CARD_H);
    if ( pg == (game.getPileGroup("waste")) )      return new Rect(LEFT + 40 + CARD_W,           TOP + 20, CARD_W, CARD_H);
    if ( pg == (game.getPileGroup("tableau")) )    return new Rect(LEFT + 20,                    BOTTOM - (CARD_H + (SYMBOL_H*20)) - 20, 7*(CARD_W + 20), CARD_H + (SYMBOL_H*20));
    return null;
  }

  public Rect getBounds(Pile p) {
    if ( game.getPileGroup("foundation").contains(p) ) {
      Rect bounds = getBounds(game.getPileGroup("foundation"));
      return new Rect(bounds.x + game.getPileGroup("foundation").indexOf(p)*(CARD_W+20), bounds.y, CARD_W, CARD_H);
    }
    if ( game.getPileGroup("waste") != null && game.getPileGroup("waste").contains(p) ) {
      Rect bounds = getBounds(game.getPileGroup("waste"));
      return new Rect(bounds.x, bounds.y, CARD_W + 3*SYMBOL_W, CARD_H);
    }
    if ( game.getPileGroup("tableau").contains(p) ) {
      Rect bounds = getBounds(game.getPileGroup("tableau"));
      return new Rect(bounds.x + game.getPileGroup("tableau").indexOf(p)*(CARD_W+20), bounds.y, CARD_W, CARD_H + (SYMBOL_H*p.size()));
    }
    return null;
  }

  public Rect getBounds(Pile p, int index) {
    if ( game.getPileGroup("foundation").contains(p) )  {
      Rect bounds = getBounds(game.getPileGroup("foundation"));
      return new Rect(bounds.x + game.getPileGroup("foundation").indexOf(p)*(CARD_W+20), bounds.y, CARD_W, CARD_H);
    }
    if ( game.getPileGroup("waste") != null && game.getPileGroup("waste").contains(p) ) {
      Rect bounds = getBounds(game.getPileGroup("waste"));
      int n = (p.size() - index);
      n = 3 - n < 0 ? 0 : 3 - n;
      return new Rect(LEFT + 40 + CARD_W + n * SYMBOL_W, TOP + 20, CARD_W, CARD_H);
    }
    if ( game.getPileGroup("tableau").contains(p) ) {
      Rect bounds = getBounds(game.getPileGroup("tableau"));
      if ( !p.get(index).faceup ) return new Rect(bounds.x + game.getPileGroup("tableau").indexOf(p)*(CARD_W+20),
                                                  bounds.y + (3*index),
                                                  CARD_W,
                                                  3);
      int showCount = 0;
      for ( int i = p.size() - 1; i >= 0 && p.get(i).faceup; --i ) {
        showCount++;
      }
      return new Rect(bounds.x + game.getPileGroup("tableau").indexOf(p)*(CARD_W+20),
                      bounds.y + SYMBOL_H*(index - (p.size() - showCount)) + 3*(p.size() - showCount),
                      CARD_W,
                      index == game.getPileGroup("tableau").size() - 1 ? CARD_H : SYMBOL_H);
    }
    return null;
  }
  
  public void layout() {
  
  }
}

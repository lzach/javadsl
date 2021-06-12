package patience;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by ludvig on 24/06/15.
 */

class PileContext {
    int index;
    int xoff, yoff;
    Pile pile;
    public PileContext(Pile pile, int index) {
      this(pile,index, 0, 0);
    }
    public PileContext(Pile pile, int index, int x, int y) {
        this.index = index;
        this.pile = pile;
        this.xoff = x;
        this.yoff = y;
    }
}

class PileGroupContext {
  PileGroup [] pilegroups;
  
  PileGroupContext(PatGame game) {
    List<PileGroup> groups = new ArrayList<PileGroup>(3);
    if ( game.getPileGroup("tableau") != null ) groups.add(game.getPileGroup("tableau"));
    if ( game.getPileGroup("waste") != null ) groups.add(game.getPileGroup("waste"));
    if ( game.getPileGroup("foundation") != null ) groups.add(game.getPileGroup("foundation"));
    pilegroups = groups.toArray(new PileGroup[groups.size()]);
  }

  PileGroup get(int i) {
    return pilegroups[i];
  }
  
  int size() {
    return pilegroups.length;
  }
}
 
public class PatView extends JPanel {
    private PatGame game;
    private PatLayout layout;
    private PileContext fromPile = new PileContext(null, -1);
    private PileGroupContext pilegroups;
    private int mouseX, mouseY;
    private BufferedImage[] cardImages = new BufferedImage[52];
    private String[] cardNames = {"ah","2h","3h","4h","5h","6h","7h","8h","9h","10h","jh","qh","kh","ad","2d","3d","4d","5d","6d","7d","8d","9d","10d","jd","qd","kd",
                                  "ac","2c","3c","4c","5c","6c","7c","8c","9c","10c","jc","qc","kc","as","2s","3s","4s","5s","6s","7s","8s","9s","10s","js","qs","ks" };

    private final int CARD_W = 100;
    private final int CARD_H = 143;
    private final int FAN_SIZE = 5;
    
    public PatView(PatGame g) {
        layout = new KlondikeLayout(g);
        this.game = g;
        for ( int i = 0; i < 52; ++i ) {
          try {
            cardImages[i] = ImageIO.read(new File("patience/images/" + cardNames[i] + ".png"));
          } catch ( Exception e ) {
            System.out.println("Couldn't load " + cardNames[i] + ".png" );
          }
        }
        addMouseMotionListener(new MouseMotionListener() {
          public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            
            if ( fromPile.pile != null ) repaint();
          }

          public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            if ( fromPile.pile != null ) repaint();
          }

        });
        addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                  if ( layout.getBounds(game.deck).inside(e.getX(), e.getY()) ) {
                    if ( game.deck.isEmpty() ) game.redeal(game.deck);
                    else game.deal();

                    turnCards();
                    repaint();
                  } else fromPile = getPileAt(e.getX(), e.getY());
                }
                public void mouseReleased(MouseEvent e) {
                  if ( fromPile != null && fromPile.pile != null ) {
                    PileContext context = getPileAt(e.getX(), e.getY()); 
                    Pile toPile = context.pile;
                    if ( toPile != null && fromPile.pile.top() != null ) {
                        if ( fromPile.pile.size() - 1 == fromPile.index ) game.move(fromPile.pile.top(), fromPile.pile, toPile);
                        else game.move(fromPile.pile, new Pile(fromPile.pile.subList(fromPile.index, fromPile.pile.size())), toPile);
                        
                    }
                    fromPile = new PileContext(null, -1);
                    turnCards();
                    repaint();
                  }
                  if ( game.hasWon() ) {
                    System.out.println("We Won!");
                  } else if ( game.hasLost(game.deck) ) {
                    System.out.println("Lost game!");
                  } else {
                    System.out.println("Not Won Yet");
                  }
                }
        });
        pilegroups = new PileGroupContext(game);
        turnCards();
    }

    PileContext getPileAt(int x, int y) {
      for (int i = 0; i < pilegroups.size(); ++i ) {
        PileGroup pileg = pilegroups.get(i);
        if ( layout.getBounds(pileg).inside(x, y) ) {
          for ( Pile pile : pileg ) {
            if ( layout.getBounds(pile).inside(x, y) ) {
                Rect r = layout.getBounds(pile);
              for ( int ci = pile.size() - 1; ci >= 0; --ci ) {
                r = layout.getBounds(pile, ci);
                if ( r.inside(x, y) ) {
                  return new PileContext(pile, ci, x - r.x, y - r.y); 
                }
              }
              return new PileContext(pile, pile.size() - 1, x - r.x, y - r.y);
            }
          }
        }
      }
//       if ( y >= CARD_H + 100 && y <= pilegroups.size()*(CARD_H + 100) + CARD_H ) {
//         for ( int i = 1; i <= pilegroups.size(); ++i ) {
//           PileGroup group = pilegroups.get(i - 1); 
//           for ( int j = 0; j < group.size(); ++j ) {
//             if  ( y >= i * (CARD_H + 100) && y <= i *(CARD_H + 100) + CARD_H + group.get(j).size() * FAN_SIZE ) {
//               if ( x >= j*(CARD_W + 5) && x <= j*(CARD_W + 5)+ CARD_W  ) {
//                 int index = (y - i * (CARD_H + 100))/FAN_SIZE;
//                 if ( index >= group.get(j).size() && !group.get(j).isEmpty() ) index = group.get(j).size() - 1;
//                 else if ( group.get(j).isEmpty() ) index = 0;
//                 
//                 //System.out.println("Index: " + index + "; Card: " + group.get(j).get(index) );
//                 return new PileContext(group.get(j), index);
//               }
//             }
//           }
//         }
//       }
      return new PileContext(null, -1);
    }
    
    protected void turnCards() {
      for (int i = 0; i < pilegroups.size(); ++i ) {
          PileGroup pileg = pilegroups.get(i);
          for ( Pile pile : pileg ) {
            for ( Card card : pile ) {
              if ( game.shouldTurn(pile, card) ) {
                card.faceup = true;
              }
            }
          }
      }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rect r = layout.getBounds(game.deck);
        g.fillRect(r.x, r.y, CARD_W , CARD_H );
       
        for ( int i = 0; i < pilegroups.size(); ++i ) {
            PileGroup pg = pilegroups.get(i);
            int j = 0;
            for ( Pile p : pg ) {
//                 g.fillRect(j * (CARD_W + 5), (i+1) * (CARD_H + 100) , CARD_W , CARD_H );
                r = layout.getBounds(p);
               // System.out.println("" + i + ": "+ j + ":" + r);
                g.fillRect(r.x, r.y, CARD_W, CARD_H);
                for ( int n = 0; n < p.size(); ++n) {
                    if ( p.equals(fromPile.pile) && n >= fromPile.index ) {
                      break;
                    }
                    r = layout.getBounds(p, n);
                    if ( !p.get(n).faceup ) g.fillRect(r.x, r.y , r.w , r.h);
                    else g.drawImage(cardImages[p.get(n).id()], r.x, r.y, null);
                    //if ( !p.get(n).faceup ) g.fillRect(j * (CARD_W + 5), (i+1) * (CARD_H + 100) , CARD_W , CARD_H + n*FAN_SIZE );
                    //else g.drawImage(cardImages[p.get(n).id()], j * (CARD_W + 5), (i+1) * (CARD_H + 100) + n*FAN_SIZE, null);
                }
                ++j;
            }
        }
         if ( fromPile.pile != null ) {
          int deltaY = 0;
        //  System.out.println("mx: " + mouseX + "; my: " + mouseY);
          for ( int n = fromPile.index; n < fromPile.pile.size(); ++n) {
                    r = layout.getBounds(fromPile.pile, n);
                    if ( !fromPile.pile.get(n).faceup ) g.fillRect(mouseX - fromPile.xoff, mouseY -fromPile.yoff + deltaY, r.w , r.h);
                    else g.drawImage(cardImages[fromPile.pile.get(n).id()], mouseX - fromPile.xoff, mouseY -fromPile.yoff + deltaY, null);
                    deltaY += r.h;
          }
        }
    }
}

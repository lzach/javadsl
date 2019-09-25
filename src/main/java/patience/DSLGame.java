package patience;
import java.util.ArrayList;
import java.util.List;
//import org.antlr.runtime.*;
import org.antlr.v4.runtime.*;
import patience.parser.*;
import java.util.Scanner;
import java.io.FileReader;

/**
 * Created by ludvig on 03/03/15.
 */
public abstract class DSLGame extends PatGame{
    //ByteCode code;

//     PatParser dealParser;
//     PatParser moveParser;
//     PatParser winParser;
//     PatParser turnParser;
//     PatParser setupParser;
//     PatParser pilegroupsParser;
//     
//     PileGroup [] pilegroups = null;
//     enum InputType {
//         DEAL,
//         MOVE,
//         WIN,
//         TURN,
//         SETUP,
//         PILEGROUP,
//         NONE,
//     };
//     public DSLGame(String filename) {
//        // this.code = code;
//        Scanner in = null; 
//         try {
//         in = new Scanner(new FileReader(filename)); } catch (Exception e) {}
//         in.useDelimiter("");
//         String inputstr = "";
//        
//         InputType type = InputType.NONE;
//         while ( in.hasNext() ) {
//           char c = in.next().charAt(0);
//           
//           if ( (c == ' ' || c == '\t' || c == '\n' || c == '\r') && type.equals(InputType.NONE) ) continue;
//           if ( type == InputType.NONE && c == 'd' ) {
//             type = InputType.DEAL;
//           } else if (type == InputType.NONE && c == 'c' ) {
//             type = InputType.MOVE;
//           } else if ( type == InputType.NONE && c == 'w' ) {
//             type = InputType.WIN;
//           } else if (type == InputType.NONE &&  c == 't' ) {
//             type = InputType.TURN;
//           } else if (type == InputType.NONE && c == 's'  ) {
//             type = InputType.SETUP;
//           } else if (type == InputType.NONE &&  c == 'p' ) {
//             type = InputType.PILEGROUP;
//           }
//           if ( c == ';' ) {
//             inputstr += c;
//       
//             System.out.println(inputstr);
//             switch (type) {
//               case DEAL:
//                 if ( dealParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else dealParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//               case MOVE:
//                 if ( moveParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else moveParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//               case WIN:
//                 if ( winParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else winParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//               case TURN:
//                 if ( turnParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else turnParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//               case SETUP:
//                 if ( setupParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else setupParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//               case PILEGROUP:
//                 if ( pilegroupsParser != null ) System.out.println("Only one declaration of each function allowed");
//                 else pilegroupsParser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(inputstr))));
//                 break;
//             }
//             inputstr = "";
//             type = InputType.NONE;
//           } else {
//             inputstr += c;
//           }
//         }
//         pileGroups();
//       //  scanner.close();
//         //parser.parse();
//     }
// 
//     @Override
//     public void deal(Deck deck) {
//        // code.execute("deal");
//        dealParser.setParams(new Deck[]{deck});
//        dealParser.parse();
//        dealParser.reset();
//     }
// 
//     @Override
//     protected boolean canMove(Card card, Pile pile) {
//         moveParser.setParams(new Object[]{card, pile});
//         boolean ret = (Boolean)moveParser.parse().value;
//         moveParser.reset();
//         return ret;
//     }
// 
// //     @Override
// //     protected boolean canMove(Pile mpile, Pile pile1, Pile pile2) {
// //         return code.execute("canMove", mpile, pile1, pile2);
// //     }
//     
//     //abstract boolean canMove(Card card, Pile pile);
//     //abstract void deal(Deck deck);
//     void setup(Deck deck) {
//         setupParser.setParams(new Object[]{deck});
//         setupParser.parse();
//         setupParser.reset();
//     }
//     
//     boolean hasWon() {
//         winParser.setParams(new Object[]{});
//         boolean ret = (Boolean)winParser.parse().value;
//         winParser.reset();
//         return ret; 
//       
//     }
//     boolean shouldTurn(Pile pile) {
//         turnParser.setParams(new Object[]{pile});
//         boolean ret = (Boolean)turnParser.parse().value;
//         turnParser.reset();
//         return ret; 
//     }
//     PileGroup [] pileGroups()  {
//       if ( pilegroups == null ) {
//         pilegroupsParser.parse();
//         List<PileGroup> groups = new ArrayList<PileGroup>();
//         for ( Object o : pilegroupsParser.ids().values() ) {
//           if ( o instanceof PileGroup ) {
//             groups.add((PileGroup)o);
//           }
//         
//         }
//         pilegroups = groups.toArray(new PileGroup[groups.size()]);
//       }
//       return pilegroups;
//     }
}

// class ByteCode {
//     // stub representing compiled DSL
//     public void execute(String method) {
// 
//     }
// 
//     public boolean execute(String method, Object... arguments) {
//         return false;
//     }
// }

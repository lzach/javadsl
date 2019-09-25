package patience.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.*;
import patience.parser.*;

import java.util.Scanner;
import java.io.FileReader;
import java.io.StringBufferInputStream;

import patience.PatGame;
import patience.*;

import java.util.Map;
import java.util.HashMap;

public class DSLPatGame extends PatGame {
    private DSLContext context;
    private VariableMethod canMoveCMethod = null;
    private VariableMethod canMovePMethod = null;
    private VariableMethod dealMethod = null;
    private VariableMethod setupMethod = null;
    private VariableMethod winMethod = null;
    private VariableMethod loseMethod = null;
    private VariableMethod turnMethod = null;
    private VariableMethod redealMethod = null;
    private VariableMethod pilegroupsMethod = null;
    private PileGroup[] _pilegroups = null;

    public DSLPatGame(String filename) {
        PatParser parser = null;
        context = new DSLContext(new HashMap<String, Object>());
        try {
            parser = new PatParser(new CommonTokenStream(new PatLexer(new ANTLRInputStream(new FileReader(filename)))));
        } catch (Exception e) {
        }
        if (parser != null) {
            List<VariableMethod> methods = parser.parse().methods;
            for (VariableMethod method : methods) {
                if (method.getName().equals("canMoveC")) {
                    if (canMoveCMethod == null) canMoveCMethod = method;
                    else System.out.println("canMoveC defined twice!");
                    canMoveCMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new CardDomain(), new PileDomain(), new PileGroupDomain(), new OrGuardedDomain(), new intDomain(), new booleanDomain(),})));
                    canMoveCMethod.setIds(new String[]{"card", "pile",}, new DSLType[]{new CardType(), new PileType(),});
                }
                if (method.getName().equals("canMoveP")) {
                    if (canMovePMethod == null) canMovePMethod = method;
                    else System.out.println("canMoveP defined twice!");
                    canMovePMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new CardDomain(), new PileDomain(), new PileGroupDomain(), new OrGuardedDomain(), new intDomain(), new booleanDomain(),})));
                    canMovePMethod.setIds(new String[]{"from", "to",}, new DSLType[]{new PileType(), new PileType(),});
                }
                if (method.getName().equals("deal")) {
                    if (dealMethod == null) dealMethod = method;
                    else System.out.println("deal defined twice!");
                    dealMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new DeckDomain(), new PileGroupDomain(), new intDomain(),})));
                    dealMethod.setIds(new String[]{"deck",}, new DSLType[]{new DeckType(),});
                }
                if (method.getName().equals("setup")) {
                    if (setupMethod == null) setupMethod = method;
                    else System.out.println("setup defined twice!");
                    setupMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new DeckDomain(), new PileGroupDomain(), new intDomain(), new RepeatDomain(), new ForEachDomain(),})));
                    setupMethod.setIds(new String[]{"deck",}, new DSLType[]{new DeckType(),});
                }
                if (method.getName().equals("win")) {
                    if (winMethod == null) winMethod = method;
                    else System.out.println("win defined twice!");
                    winMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new CardDomain(), new PileDomain(), new PileGroupDomain(), new intDomain(), new booleanDomain(),})));
                    winMethod.setIds(new String[]{}, new DSLType[]{});
                }
                if (method.getName().equals("lose")) {
                    if (loseMethod == null) loseMethod = method;
                    else System.out.println("lose defined twice!");
                    loseMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new CardDomain(), new PileDomain(), new PileGroupDomain(), new intDomain(), new booleanDomain(),})));
                    loseMethod.setIds(new String[]{"deck",}, new DSLType[]{new DeckType(),});
                }
                if (method.getName().equals("turn")) {
                    if (turnMethod == null) turnMethod = method;
                    else System.out.println("turn defined twice!");
                    turnMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new CardDomain(), new PileDomain(), new PileGroupDomain(), new intDomain(), new booleanDomain(),})));
                    turnMethod.setIds(new String[]{"pile", "card",}, new DSLType[]{new PileType(), new CardType(),});
                }
                if (method.getName().equals("redeal")) {
                    if (redealMethod == null) redealMethod = method;
                    else System.out.println("redeal defined twice!");
                    redealMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new DeckDomain(), new PileGroupDomain(), new intDomain(),})));
                    redealMethod.setIds(new String[]{"deck",}, new DSLType[]{new DeckType(),});
                }
                if (method.getName().equals("pilegroups")) {
                    if (pilegroupsMethod == null) pilegroupsMethod = method;
                    else System.out.println("pilegroups defined twice!");
                    pilegroupsMethod.setContext(new DSLContext(context.ids(), new DomainUnion(new DSLDomain[]{new PileGroupDomain(),})));
                    pilegroupsMethod.setIds(new String[]{}, new DSLType[]{});
                }
            }
        }
        if (canMoveCMethod == null || canMovePMethod == null || dealMethod == null || setupMethod == null || winMethod == null || loseMethod == null || turnMethod == null || redealMethod == null || pilegroupsMethod == null) {
            System.out.println("Not all methods defined!");
        }
        pilegroupsMethod.eval(new Object[]{});
        System.out.println(pilegroupsMethod.context.trace);
    }

    @Override
    public boolean canMove(Card card, Pile pile) {
        List<Code> codes = canMoveCMethod.getCodes();
        VariableMethod _varMethod = canMoveCMethod;
        System.out.println(codes.toString());
        canMoveCMethod.setParams(new Object[]{card, pile,});
        Object _retval = null;
        try {
            Code _code1 = codes.get(0);
            System.out.println(_code1);
            for (Code _code2 : _code1) {
                if ((boolean) ((PileGroup) _varMethod.evalVar(_code2.get(0))).contains(pile)) {
                    if ((boolean) pile.isEmpty()) {
                        return (boolean) (_varMethod.evalExpr(_code2.get(1).get(0)));
                    } else {
                        return (boolean) (_varMethod.evalExpr(_code2.get(1).get(1)));
                    }
                }
            }
        } catch (ReturnValue val) {
            return (boolean) val.value;
        }
        return (boolean) _retval;
    }

    @Override
    public boolean canMovePile(Pile from, Pile to) {
        List<Code> codes = canMovePMethod.getCodes();
        VariableMethod _varMethod = canMovePMethod;
        System.out.println(codes.toString());
        canMovePMethod.setParams(new Object[]{from, to,});
        Object _retval = null;
        try {
            Code _code3 = codes.get(0);
            System.out.println(_code3);
            for (Code _code4 : _code3) {
                if ((boolean) ((PileGroup) _varMethod.evalVar(_code4.get(0))).contains(to)) {
                    if ((boolean) to.isEmpty()) {
                        return (boolean) (_varMethod.evalExpr(_code4.get(1).get(0)));
                    } else {
                        return (boolean) (_varMethod.evalExpr(_code4.get(1).get(1)));
                    }
                }
            }
        } catch (ReturnValue val) {
            return (boolean) val.value;
        }
        return (boolean) _retval;
    }

    @Override
    public void deal(Deck deck) {
        List<Code> codes = dealMethod.getCodes();
        VariableMethod _varMethod = dealMethod;
        System.out.println(codes.toString());
        dealMethod.setParams(new Object[]{deck,});
        try {
            Code _code5 = codes.get(0);
            System.out.println(_code5);
            {
                int index = 0;
                for (Object pile : (List) ((PileGroup) _varMethod.evalVar(_code5.get(0)))) {
                    context.ids().put("index", index);
                    context.ids().put("pile", pile);
                    {
                        int counter = 0;
                        while (counter < (int) (_varMethod.evalExpr(_code5.get(1)))) {
                            context.ids().put("counter", counter);
                            ((Pile) pile).add(deck.deal());
                            counter++;
                        }
                    }
                    ;
                    index++;
                }
            }
        } catch (ReturnValue val) {
            System.out.println("deal cannot return a value");
        }

    }

    @Override
    public void setup(Deck deck) {
        List<Code> codes = setupMethod.getCodes();
        VariableMethod _varMethod = setupMethod;
        System.out.println(codes.toString());
        setupMethod.setParams(new Object[]{deck,});
        try {
            Code _code8 = codes.get(0);
            System.out.println(_code8);
            {
                int index = 0;
                for (Object pile : (List) ((PileGroup) _varMethod.evalVar(_code8.get(0)))) {
                    context.ids().put("index", index);
                    context.ids().put("pile", pile);
                    {
                        int counter = 0;
                        while (counter < (int) (_varMethod.evalExpr(_code8.get(1)))) {
                            context.ids().put("counter", counter);
                            ((Pile) pile).add(deck.deal());
                            counter++;
                        }
                    }
                    ;
                    index++;
                }
            }
        } catch (ReturnValue val) {
            System.out.println("setup cannot return a value");
        }

    }

    @Override
    public boolean hasWon() {
        List<Code> codes = winMethod.getCodes();
        VariableMethod _varMethod = winMethod;
        System.out.println(codes.toString());
        winMethod.setParams(new Object[]{});
        Object _retval = null;
        try {
            Code _code11 = codes.get(0);
            System.out.println(_code11);
            {
                int index = 0;
                for (Object pile : (List) ((PileGroup) _varMethod.evalVar(_code11.get(0)))) {
                    context.ids().put("index", index);
                    context.ids().put("pile", pile);
                    if ((boolean) !(((boolean) _varMethod.evalExpr(_code11.get(1).get(0))))) {
                        return (boolean) false;
                    }
                    ;
                    index++;
                }
            }
            Code _code13 = codes.get(0);
            System.out.println(_code13);
            _retval = true;
        } catch (ReturnValue val) {
            return (boolean) val.value;
        }
        return (boolean) _retval;
    }

    @Override
    public boolean hasLost(Deck deck) {
        List<Code> codes = loseMethod.getCodes();
        VariableMethod _varMethod = loseMethod;
        System.out.println(codes.toString());
        loseMethod.setParams(new Object[]{deck,});
        Object _retval = null;
        try {
            Code _code14 = codes.get(0);
            System.out.println(_code14);
            for (Code _code15 : _code14) {
                _varMethod.evalStmt(_code15.get(0));
            }
        } catch (ReturnValue val) {
            return (boolean) val.value;
        }
        return (boolean) _retval;
    }

    @Override
    public boolean shouldTurn(Pile pile, Card card) {
        List<Code> codes = turnMethod.getCodes();
        VariableMethod _varMethod = turnMethod;
        System.out.println(codes.toString());
        turnMethod.setParams(new Object[]{pile, card,});
        Object _retval = null;
        try {
            Code _code16 = codes.get(0);
            System.out.println(_code16);
            for (Code _code17 : _code16) {
                _varMethod.evalStmt(_code17.get(0));
            }
        } catch (ReturnValue val) {
            return (boolean) val.value;
        }
        return (boolean) _retval;
    }

    @Override
    public void redeal(Deck deck) {
        List<Code> codes = redealMethod.getCodes();
        VariableMethod _varMethod = redealMethod;
        System.out.println(codes.toString());
        redealMethod.setParams(new Object[]{deck,});
        try {
            Code _code18 = codes.get(0);
            System.out.println(_code18);
            for (Code _code19 : _code18) {
                _varMethod.evalStmt(_code19.get(0));
            }
        } catch (ReturnValue val) {
            System.out.println("redeal cannot return a value");
        }

    }

    @Override
    public PileGroup getPileGroup(String name) {
        return (PileGroup) context.ids().get(name);
    }
}

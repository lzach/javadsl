package patience.interpreter;
import java.util.List;
import patience.*;
import java.util.List;
import java.util.ArrayList;
class CardDomain extends DSLDomain {
	public DSLType getType(String id) {
		if ( id.equals("HEARTS") ) return new CardSuitType();
		if ( id.equals("DIAMONDS") ) return new CardSuitType();
		if ( id.equals("CLUBS") ) return new CardSuitType();
		if ( id.equals("SPADES") ) return new CardSuitType();
		if ( id.equals("BLACK") ) return new CardColourType();
		if ( id.equals("RED") ) return new CardColourType();
		if ( id.equals("King") ) return new intType();
		if ( id.equals("Queen") ) return new intType();
		if ( id.equals("Jack") ) return new intType();
		if ( id.equals("Ace") ) return new intType();
		if ( id.equals("Card") ) return new CardConstructorType();
		return null;
	}
	public Object eval(String id) {
		if ( id.equals("HEARTS") ) return Card.Suit.HEARTS;
		if ( id.equals("DIAMONDS") ) return Card.Suit.DIAMONDS;
		if ( id.equals("CLUBS") ) return Card.Suit.CLUBS;
		if ( id.equals("SPADES") ) return Card.Suit.SPADES;
		if ( id.equals("BLACK") ) return Card.Colour.BLACK;
		if ( id.equals("RED") ) return Card.Colour.RED;
		if ( id.equals("King") ) return 13;
		if ( id.equals("Queen") ) return 12;
		if ( id.equals("Jack") ) return 11;
		if ( id.equals("Ace") ) return 1;
		return null;
	}
	public Object eval(DSLContext context, String id, List<Code> codes) {
		if ( id.equals("Card") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			args.add(context.eval(codes.get(1)));
			children.add(context.trace);
			Object o = new Card((int)args.get(0),(Card.Suit)args.get(1));
			context.trace = new ExecutionTraceTree(id, ""+o, false, children);
			return o;
		}
		return null;
	}
	public Object eval(DSLContext context, Object obj, String member, List<Code> codes) {
		if ( member.equals("value") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = ((Card)obj).value;
			context.trace = new ExecutionTraceTree(member, ""+o, true, children);
			return o;
		}
		if ( member.equals("suit") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = ((Card)obj).suit;
			context.trace = new ExecutionTraceTree(member, ""+o, true, children);
			return o;
		}
		if ( member.equals("colour") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = ((Card)obj).colour;
			context.trace = new ExecutionTraceTree(member, ""+o, true, children);
			return o;
		}
		if ( member.equals("faceup") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = ((Card)obj).faceup;
			context.trace = new ExecutionTraceTree(member, ""+o, true, children);
			return o;
		}
		return null;
	}
}
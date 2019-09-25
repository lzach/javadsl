package patience.interpreter;
import java.util.List;
import patience.*;
import java.util.List;
import java.util.ArrayList;
class DeckDomain extends ListDomain<Card> {
	public DSLType getType(String id) {
		if ( id.equals("Deck") ) return new DeckConstructorType();
		return null;
	}
	public Object eval(String id) {
		return super.eval(id);
	}
	public Object eval(DSLContext context, String id, List<Code> codes) {
		if ( id.equals("Deck") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = new Deck();
			context.trace = new ExecutionTraceTree(id, ""+o, false, children);
			return o;
		}
		return super.eval(context,id,codes);
	}
	public Object eval(DSLContext context, Object obj, String member, List<Code> codes) {
		if ( member.equals("deal") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = ((Deck)obj).deal();
			context.trace = new ExecutionTraceTree(member, ""+o, false, children);
			return o;
		}
		if ( member.equals("putTopC") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			((Deck)obj).putTop((Card)args.get(0));
			context.trace = new ExecutionTraceTree(member, "", false, children);
			return null;
		}
		if ( member.equals("putBottomC") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			((Deck)obj).putBottom((Card)args.get(0));
			context.trace = new ExecutionTraceTree(member, "", false, children);
			return null;
		}
		if ( member.equals("putTopP") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			((Deck)obj).putTop((Pile)args.get(0));
			context.trace = new ExecutionTraceTree(member, "", false, children);
			return null;
		}
		if ( member.equals("putBottomP") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			((Deck)obj).putBottom((Pile)args.get(0));
			context.trace = new ExecutionTraceTree(member, "", false, children);
			return null;
		}
		return super.eval(context,obj,member,codes);
	}
}
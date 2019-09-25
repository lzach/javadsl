package patience.interpreter;
import java.util.List;
import patience.*;
import java.util.List;
import java.util.ArrayList;
class PileDomain extends ListDomain<Card> {
	public DSLType getType(String id) {
		if ( id.equals("Pile") ) return new PileConstructorType();
		return null;
	}
	public Object eval(String id) {
		return super.eval(id);
	}
	public Object eval(DSLContext context, String id, List<Code> codes) {
		if ( id.equals("Pile") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			Object o = new Pile();
			context.trace = new ExecutionTraceTree(id, ""+o, false, children);
			return o;
		}
		return super.eval(context,id,codes);
	}
	public Object eval(DSLContext context, Object obj, String member, List<Code> codes) {
		return super.eval(context,obj,member,codes);
	}
}
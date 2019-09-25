package patience.interpreter;
import java.util.List;
import patience.*;
import java.util.List;
import java.util.ArrayList;
class PileGroupDomain extends ListDomain<Pile> {
	public DSLType getType(String id) {
		if ( id.equals("PileGroup") ) return new PileGroupConstructorType();
		return null;
	}
	public Object eval(String id) {
		return super.eval(id);
	}
	public Object eval(DSLContext context, String id, List<Code> codes) {
		if ( id.equals("PileGroup") ) {
			List<Object> args = new ArrayList<Object>();
			List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
			args.add(context.eval(codes.get(0)));
			children.add(context.trace);
			Object o = new PileGroup((int)args.get(0));
			context.trace = new ExecutionTraceTree(id, ""+o, false, children);
			return o;
		}
		return super.eval(context,id,codes);
	}
	public Object eval(DSLContext context, Object obj, String member, List<Code> codes) {
		return super.eval(context,obj,member,codes);
	}
}
package patience.interpreter;
import java.util.List;
import java.util.ArrayList;

public abstract class DSLDomain {
    public abstract DSLType getType(String id);
    
    public abstract Object eval(String id);
    public abstract Object eval(DSLContext context, String id, List<Code> args);
    public abstract Object eval(DSLContext context, Object obj, String member, List<Code> args);
}

class intDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("lt") ) {
            return new intType();
        } else if ( id.equals("lte") ) {
            return new intType();
        } else if ( id.equals("gt") ) {
            return new intType();
        } else if ( id.equals("gte") ) {
            return new intType();
        } else if ( id.equals("add") ) {
            return new intType();
        } else if ( id.equals("sub") ) {
            return new intType();
        } else if ( id.equals("mul") ) {
            return new intType();
        } else if ( id.equals("div") ) {
            return new intType();
        }else if ( id.equals("neg") ) {
            return new intType();
        }else if ( id.equals("inc") ) {
            return new intType();
        }else if ( id.equals("dec") ) {
            return new intType();
        }
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {

        if ( id.equals("lt") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 < i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 < i2;
        } else if ( id.equals("lte") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 <= i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 <= i2;
        } else if ( id.equals("gt") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 > i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 > i2;
        } else if ( id.equals("gte") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 >= i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 >= i2;
        } else if ( id.equals("add") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 + i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 + i2;
        } else if ( id.equals("sub") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 - i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 - i2;
        } else if ( id.equals("mul") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 * i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 * i2;
        } else if ( id.equals("div") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            int i2 = (Integer)context.eval(args.get(1));
            ExecutionTraceTree t2 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 / i2), false, new ExecutionTraceTree[]{t1, t2});
            return i1 / i2;
        }  else if ( id.equals("neg") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + -i1 , false, new ExecutionTraceTree[]{ t1 });
            return -i1;
        } else if ( id.equals("inc") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 + 1) , false, new ExecutionTraceTree[]{ t1 });
            return i1 + 1;
        } else if ( id.equals("dec") ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + (i1 - 1) , false, new ExecutionTraceTree[]{ t1 });
            return i1 - 1;
        } 
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        return null;
    }

}
class booleanDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("and") ) {
            return new booleanType();
        } else if ( id.equals("not") ) {
            return new booleanType();
        } else if ( id.equals("or") ) {
            return new booleanType();
        }
        return null;
    }
    public Object eval(String id) {
        if ( id.equals("true")  ) return true;
        if ( id.equals("false") ) return false;
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        if ( id.equals("and") ) {
            boolean b1 = (Boolean)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            boolean b2 = false;
            if ( b1 ) {
              b2 = (Boolean)context.eval(args.get(1));
              ExecutionTraceTree t2 = context.trace;
              context.trace = new ExecutionTraceTree(id, "" + (b1 && b2), false, new ExecutionTraceTree[]{t1, t2});
            } else {
              context.trace = new ExecutionTraceTree(id, "" + b1, false, new ExecutionTraceTree[]{t1,});
            }
            return b1 && b2;
        } else if ( id.equals("or") ) {
            boolean b1 = (Boolean)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            boolean b2 = false;
            if ( !b1 ) {
              b2 = (Boolean)context.eval(args.get(1));
              ExecutionTraceTree t2 = context.trace;
              context.trace = new ExecutionTraceTree(id, "" + (b1 || b2), false, new ExecutionTraceTree[]{t1, t2});
            } else {
              context.trace = new ExecutionTraceTree(id, "" + b1, false, new ExecutionTraceTree[]{t1,});
            }
            return b1 || b2;
        } else if ( id.equals("not") ) {
            boolean b1 = (Boolean)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree(id, "" + !b1, false, new ExecutionTraceTree[]{t1,});
            return !b1;
        }        
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        return null;
    }

}
class voidDomain extends DSLDomain {
    public DSLType getType(String id) {
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) { 
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        return null;
    }
}
class DomainUnion extends DSLDomain {
    private final DSLDomain []domains;
    public DomainUnion(DSLDomain []domains) {
        this.domains = domains;
    }
    public DSLType getType(String id) {
        DSLType type;
        for ( DSLDomain domain : domains ){ 
            type = domain.getType(id);
            if ( type != null ) return type;
        }
        return null;
    }
    public Object eval(String id) {
        Object obj;
        for ( DSLDomain domain : domains ){ 
            obj = domain.eval(id);
            if ( obj != null ) return obj;
        }
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
     //   System.out.print(id);
        Object obj;
        for ( DSLDomain domain : domains ){ 
            obj = domain.eval(context, id, args);
            if ( obj != null ) return obj;
        }
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args){
        Object ret;
        //System.out.print("" + obj + "." + member );
 
        for ( DSLDomain domain : domains ){ 
            ret = domain.eval(context, obj, member, args);
            if ( ret != null ) return ret;
        }
        return null;
    }
}
class ListDomain<T> extends DSLDomain {
    public DSLType getType(String id) {
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        //if ( obj == null ) return null;
        if ( member.equals("bottom")   ) {
            context.trace = new ExecutionTraceTree("bottom", "" +((List<T>)obj).get(0), false, new ExecutionTraceTree[]{});
            return ((List<T>)obj).get(0);
        }
        if ( member.equals("top")      ) {
            context.trace = new ExecutionTraceTree("top", "" +((List<T>)obj).get(((List<T>)obj).size()-1), false, new ExecutionTraceTree[]{});
            return ((List<T>)obj).get(((List<T>)obj).size()-1);
        }
        if ( member.equals("at")       ) {
            int i1 = (Integer)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree("at", "" +((List<T>)obj).get(i1), false, new ExecutionTraceTree[]{t1,});
            return ((List<T>)obj).get(i1);
        }
        if ( member.equals("empty")    ) {
            context.trace = new ExecutionTraceTree("empty", "" +((List<T>)obj).isEmpty(), false, new ExecutionTraceTree[]{});
            return ((List<T>)obj).isEmpty();
        }
        if ( member.equals("add")      ) {
            T o1 = (T)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree("add", "", false, new ExecutionTraceTree[]{t1,});
            return ((List<T>)obj).add(o1);
        }
        if ( member.equals("contains") ) {
            T o1 = (T)context.eval(args.get(0));
            ExecutionTraceTree t1 = context.trace;
            context.trace = new ExecutionTraceTree("contains", "" +((List<T>)obj).contains(o1), false, new ExecutionTraceTree[]{t1,});
            return ((List<T>)obj).contains(o1);
        }
        if ( member.equals("all")      ) {
            List<T> group = (List<T>)obj;
            List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
            obj = true;
            context.ids().put("prev", null);
            context.ids().put("next", group.isEmpty() ? null : group.get(0) );
            for ( int i = 0; i < group.size(); ++i ) {
                context.ids().put("next", (i + 1 >= group.size()) ? null : group.get(i+1) );
                context.ids().put("current", group.get(i));
                obj = (Boolean)obj && (Boolean)context.eval( args.get(0) );
                children.add(context.trace);
                context.ids().put("prev", group.get(i));
            }
            context.ids().put("prev", null);
            context.trace = new ExecutionTraceTree("all", "" + obj, false, children);
            return obj;
        }
        if ( member.equals("exist")      ) {
            List<T> group = (List<T>)obj;
            List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
            obj = true;
            context.ids().put("prev", null);
            context.ids().put("next", group.isEmpty() ? null : group.get(0) );
            for ( int i = 0; i < group.size(); ++i ) {
                context.ids().put("next", (i + 1 >= group.size()) ? null : group.get(i+1) );
                context.ids().put("current", group.get(i));
                if ( (Boolean)context.eval( args.get(0) ) ) {
                  children.add(context.trace);
                  context.trace = new ExecutionTraceTree("exist", "" + true, false, children);
                  return true;
                }
                context.ids().put("prev", group.get(i));
            }
            context.ids().put("prev", null);
            context.trace = new ExecutionTraceTree("exist", "" + false, false, children);
            return false;
        }
        return null;
    }
}
class OrListDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("OrList") ) return new OrListType();
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        if ( id.equals("OrList") ) {
        
          List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
          for ( Code code : args ) {
            if ( (Boolean)context.eval(code) ) {
              children.add(context.trace);
              context.trace = new ExecutionTraceTree("OrList", "true", false, children);
              return true;
            }
            children.add(context.trace);
          }
          context.trace = new ExecutionTraceTree("OrList", "false", false, children);
          return false;
        }
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        //if ( obj == null ) return null;
        return null;
    }
}

class OrGuardedDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("OrGuarded") ) return new OrGuardedType();
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        if ( id.equals("guarded") ) {
        
          List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
          for ( Code code : args ) {
            if ( (Boolean)context.eval(code.getCodes().get(0)) ) {
              ExecutionTraceTree t1 = context.trace;
              Object o = context.eval(code.getCodes().get(1));
              children.add(new ExecutionTraceTree("t", "", false, new ExecutionTraceTree[]{t1, context.trace}));
              context.trace = new ExecutionTraceTree("guarded", "" +o, false, children);
              return o;
            }
            children.add(context.trace);
          }
          context.trace = new ExecutionTraceTree("guarded", "false", false, children);
          return false;
        }
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        //if ( obj == null ) return null;
        return null;
    }
}
class RepeatDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("repeat") ) return new RepeatType();
        if ( id.equals("srepeat") ) return new RepeatType();
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        if ( id.equals("repeat") ) {
          List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
          int counter = 0;
          Object obj = null;
          while ( counter < (int)context.eval(args.get(0)) ) {
            context.ids().put("counter", counter);
            obj = context.eval(args.get(1));
            children.add(context.trace);
            counter++;
          }
          context.trace = new ExecutionTraceTree("repeat", "" +obj, false, children);
          return obj;
        }
        if ( id.equals("srepeat") ) {
          List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
          int counter = (int)context.eval(args.get(0));
          Object obj = null;
          for (int i = 0; i < counter; ++i ) {
            context.ids().put("counter", i);
            obj = context.eval(args.get(1));
            children.add(context.trace);
          }
          context.trace = new ExecutionTraceTree("srepeat", "" +obj, false, children);
          return obj;
        }
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        //if ( obj == null ) return null;
        return null;
    }
}

class ForEachDomain extends DSLDomain {
    public DSLType getType(String id) {
        if ( id.equals("foreach") ) return new ForEachType();
        return null;
    }
    public Object eval(String id) {
        return null;
    }
    public Object eval(DSLContext context, String id, List<Code> args) {
        if ( id.equals("foreach") ) {
          List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
          Object obj = null;
          Object item = null;
          int index = 0;
          for ( Object o : (List)context.eval(args.get(0)) ) {
            context.ids().put("index", index);
            context.ids().put("item", o);
            obj = context.eval(args.get(1));
            children.add(context.trace);
            index++;
          }
          context.trace = new ExecutionTraceTree("foreach", "" +obj, false, children);
          return obj;
        }
        return null;
    }
    public Object eval(DSLContext context, Object obj, String member, List<Code> args) {
        //if ( obj == null ) return null;
        return null;
    }
}

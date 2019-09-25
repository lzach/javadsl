package patience.interpreter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class ReturnValue extends RuntimeException {
  Object value;
  ReturnValue(Object value) { this.value = value; }
}

class DSLContext {
  private Map<String, Object> idMap;
  private Map<String, Object> params = new HashMap<String, Object>();
  ExecutionTraceTree trace;
  static private Map<String, DSLType> idTypes = new HashMap<String, DSLType>();
  private DSLDomain domain;
  private Map<String, DSLType> paramTypes = new HashMap<String, DSLType>();

  public DSLContext(Map<String, Object> idMap) {
    this.idMap = idMap;
    this.domain = null;
  }
  public DSLContext(Map<String, Object> idMap, DSLDomain domain) {
    this.idMap = idMap;
    this.domain = domain;
  }
  public void setParams(Map<String, Object> params, Map<String, DSLType> paramTypes) {
    this.params = params;
    this.paramTypes = paramTypes;
  }
  public void printTrace() {
    System.out.println("" + trace);
  }
  public void assignType(Code code) {
    if ( code.getDSLType() != null ) return;

    if ( code.getName().equals("dot") ) {
      Code left = code.getCodes().get(0);
      Code rigth = code.getCodes().get(1);
      if ( left.getDSLType() == null ) assignType(left);

      DSLType type = left.getDSLType().getMemberType(rigth.getID());
      if ( type == null ) System.out.println("Unknown member: " + left.getDSLType().getName() +
                          " does not have a member " + rigth.getID());
      for ( String mname : type.getMemberNames() ) {
        if ( type.getMemberType(mname) instanceof ExpressionType ) {
            ExpressionType etype = (ExpressionType)type.getMemberType(mname);
            for ( String name : etype.getMemberNames() ) {
        //        System.out.println("expr-var: " + name);
                idTypes.put(name, etype.getMemberType(name));
            }
        }
      }
      if ( type instanceof MethodType )
        code.setType(((MethodType)type).getReturnType());
      else code.setType(type);
    } else if ( code.getName().equals("assign") ) {
      Code left = code.getCodes().get(0);
      Code rigth = code.getCodes().get(1);
      if ( rigth.getDSLType() == null ) assignType(rigth);

      DSLType type = rigth.getDSLType();
      if ( type instanceof MethodType )
        code.setType(((MethodType)type).getReturnType());
      else code.setType(type);
      

    } else if ( code.getName().equals("equals") ) {
      code.setType(new booleanType());
    } else if ( code instanceof Leaf && paramTypes.keySet().contains(code.getID()) ) {// check if it is a known parameter
      code.setType(paramTypes.get(code.getID()));
    } else if ( code instanceof Leaf && idTypes.keySet().contains(code.getID()) ) {// check if it is a known id
      code.setType(idTypes.get(code.getID()));
    } else if ( code instanceof Leaf && code.getName().equals("int") ) {
      code.setType(new intType());
    } else if ( domain.getType(code.getID()) != null ) {// check if it is a known function
      // TODO: this could (or likely is) a function, so should be handled as such
      DSLType type = domain.getType(code.getID());
      if ( type instanceof MethodType ) {
        MethodType mtype = (MethodType)type;
        code.setType( mtype.getReturnType() );
        int i = 0;
       // System.out.println("Code: " + code.getID() + "; type: " + code.getDSLType().getName());
        for ( Code c : code.getCodes() ) {
        // TODO: Check that this is correct type given the method type
        //  System.out.println("arg-name: " + mtype.getArgumentName(i));
          if ( mtype.getMemberType(mtype.getArgumentName(i)) instanceof ExpressionType ) {
            ExpressionType etype = (ExpressionType)mtype.getMemberType(mtype.getArgumentName(i));
            for ( String name : etype.getMemberNames() ) {
         //       System.out.println("expr-var: " + name);
                idTypes.put(name, etype.getMemberType(name));
            }
          }
          assignType(c);
          if ( !c.getDSLType().equals(mtype.getMemberType(mtype.getArgumentName(i))) ) {
            System.out.println("Does not type-check: " + code.getName() + 
                    "; arg: " + c.getID() + "; got " + c.getDSLType().getName() + ", expected "
                    + mtype.getMemberType(mtype.getArgumentName(i)).getName());
          }
        }
      } else if ( code instanceof Leaf ) {
        code.setType(domain.getType(code.getID()));
      }
    } else System.out.println("Could not type-check code: " + code.getID());
  }
  
  public Object evalLit(Code code) {
    System.out.println("evalLit: " + code);
    if ( code.getType() == Code.Types.LEAF ) return ((Leaf)code).getValue();
    return null; // should error out here!
  }
  
  public Object evalCons(Code code) {
    System.out.println("evalCons: " + code);
    return eval(code.getID(), code.getCodes());//this should check that ID is a constructor
  }
  
  public Object evalVar(Code code) {
    System.out.println("evalVar: " + code);
    if ( params.keySet().contains(code.getID()) ) {
     // trace = new ExecutionTraceTree(code.getID(), params.get(id).toString(), true, new ExecutionTraceTree[]{});
      return params.get(code.getID());
    } else if ( idMap.keySet().contains(code.getID()) ) { 
     // trace = new ExecutionTraceTree(code.getID(), idMap.get(id).toString(), true, new ExecutionTraceTree[]{});
      return idMap.get(code.getID());
    }
    return null; // should error out here!
  }
  
  public Object evalExpr(Code code) {
    System.out.println("evalExpr: " + code);
    return eval(code); // should check that outmost code returns a value
  }
  
  public Object evalStmt(Code code) {
    System.out.println("evalStmt: " + code);
    return eval(code);
  }
  
  public Object eval(List<Code> codes) {
    System.out.println("eval*: " + codes);
    Object retval = null;
    List<ExecutionTraceTree> children = new ArrayList<ExecutionTraceTree>();
    for ( Code code : codes ) { 
      retval = eval(code);
      children.add(trace);
    }
    trace = new ExecutionTraceTree("l", toString(retval), false, children);
    return retval;
  }
  public Object eval(Code code) {
    //assignType(code);
    System.out.println("eval: " + code);
    if ( code.getType() == Code.Types.LEAF ) {
        if ( code.getName().equals("id") ) {
          Object o = eval(code.getID()); 
          return o;
        } else {
          trace = new ExecutionTraceTree(((Leaf)code).getValue().toString(), "", true, new ExecutionTraceTree[]{});
          return ((Leaf)code).getValue();
        } // this does not abide by the domain rules
    } else {
        Object o = eval(code.getID(), code.getCodes());
        return o;
    }
  }
  public String getMember(Code code) {
    if ( code.getType() == Code.Types.LEAF && code.getName().equals("id") ) {
        return (String)((Leaf)code).getValue();
    } else {
        return code.getName();
    }
  }
  
  Object eval(Object obj, String member, List<Code> args) {
    System.out.println("eval-member: " + obj + ", " + member + ", " + args);
    obj = domain.eval(this, obj, member, args);
    return obj;
  }

  Object eval(String id, List<Code> args) {
    System.out.println("eval-op: " + id + ", " + args);
    if ( id.equals("equals") ) {
      Object o1 = eval(args.get(0));
      ExecutionTraceTree t1 = trace;
      Object o2 = eval(args.get(1));
      ExecutionTraceTree t2 = trace;
      
      if ( o1 == null ) {
        trace = new ExecutionTraceTree(id,""+( o1 == o2), false, new ExecutionTraceTree[]{t1, t2});
        return o1 == o2;
      }
      trace = new ExecutionTraceTree(id, "" + o1.equals(o2), false, new ExecutionTraceTree[]{t1, t2});
      return o1.equals(o2);
    } else if ( id.equals("return") ) {
      if ( args.size() != 1 ) System.out.println("Return expects one parameter");
      else throw new ReturnValue(eval(args.get(0)));
    } else if ( id.equals("dot") ) {
      if ( args.size() == 2 ) {
        Object o = eval(args.get(0));
        ExecutionTraceTree t1 = trace;
        o = eval(o, getMember(args.get(1)), args.get(1).getCodes());
        trace = new ExecutionTraceTree(id, toString(o), false, new ExecutionTraceTree[]{t1, trace});
        return o;
      } else System.out.println("Dot takes 2 arguments not " + args.size() + "(" + args.get(0).getLine() + ")");
    } else if ( id.equals("assign") ) {
      Object obj = eval(args.get(1));
      idMap.put(args.get(0).getID(), obj);
      idTypes.put(args.get(0).getID(), args.get(1).getDSLType());
      
      trace = new ExecutionTraceTree(id, "", false, new ExecutionTraceTree[]{new ExecutionTraceTree(args.get(0).getID(), "", true, new ExecutionTraceTree[]{}), trace});
      return obj;
    }
    Object o = domain.eval(this, id, args);
    return o;
  }
  
  private String toString(Object o) {
    return "" + o;
  }
  Object eval(String id) {
  System.out.println("eval-id: " + id);
    if ( params.keySet().contains(id) ) {
      trace = new ExecutionTraceTree(id, params.get(id).toString(), true, new ExecutionTraceTree[]{});
      return params.get(id);
    } else if ( idMap.keySet().contains(id) ) { 
      trace = new ExecutionTraceTree(id, idMap.get(id).toString(), true, new ExecutionTraceTree[]{});
      return idMap.get(id);
    }
   // System.out.println("Id not in params or ids: " + id);
    Object o = domain.eval(id);
    trace = new ExecutionTraceTree(id, toString(o), true, new ExecutionTraceTree[]{});
    return o;
  }
  public Map<String,Object> ids() {
    return idMap;
  }
}

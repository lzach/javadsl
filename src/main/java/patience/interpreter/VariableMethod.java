package patience.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class VariableMethod {
  private String name;
  private List<Code> code;
  private List<String> ids;
  private boolean statements;
  DSLContext context;
  private List<DSLType> types;
  
  public VariableMethod(String name, List<String> ids, List<DSLType> types, Code code) {
    this.name = name;
    this.code = new ArrayList<Code>();
    this.code.add(code);
    this.ids = ids;
    this.types = types;
    this.statements = false;
  }

  public VariableMethod(String name, Code code) {
    this.name = name;
    this.code = new ArrayList<Code>();
    this.code.add(code);
    this.ids = new ArrayList<String>();
    this.types = new ArrayList<DSLType>();
    this.statements = false;
  }
  public VariableMethod(String name, List<String> ids, List<DSLType> types, List<Code> codes) {
    this.name = name;
    this.code = codes;
    this.ids = ids;
    this.types = types;
    this.statements = true;
  }

  public VariableMethod(String name, List<Code> codes) {
    this.name = name;
    this.code = codes;
    this.ids = new ArrayList<String>();
    this.types = new ArrayList<DSLType>();
    this.statements = true;
  }

  public void setContext(DSLContext context) {
    this.context = context;
  }

  public String getName() {
    return name;
  }
  public Code getCode() {
    return code.get(0);
  }
  public List<Code> getCodes() {
    return code;
  }

  public void setIds(String []ids, DSLType []types) {
    this.ids.clear();
    this.ids.addAll(Arrays.asList(ids));
    this.types.clear();
    this.types.addAll(Arrays.asList(types));
  }

  public Map<String, Object> argsMap(Object [] args) {
    if ( args.length != ids.size() ) {
      System.out.println("Wrong number of Arguments"); 
      return null;
    }
    Map<String, Object> argmap = new HashMap<String, Object>();
    for ( int i = 0; i < args.length; ++i )
      argmap.put(ids.get(i), args[i]);
    return argmap;
  }

  public Map<String, DSLType> typesMap() {
    Map<String, DSLType> typemap = new HashMap<String, DSLType>();
    for ( int i = 0; i < ids.size(); ++i )
       typemap.put(ids.get(i), types.get(i));
    return typemap;
  }
  public void setParams(Object [] args) {
    context.setParams(argsMap(args), typesMap());
  }

  public Object eval(Object [] args) {
    context.setParams(argsMap(args), typesMap());
    Object o = null;
    try {
      o = context.eval(code);
    } catch (Exception e) {
      System.out.println("Can't complete method " + name + ";\nTrace:");
      context.printTrace();
      System.out.println("\nStack Trace:");
      e.printStackTrace();
    }
    return o;
  }

  public Object evalLit(Code code) {
    return context.evalLit(code);
  }

  public Object evalCons(Code code) {
    return context.evalCons(code);
  }

  public Object evalVar(Code code) {
    return context.evalVar(code);
  }

  public Object evalExpr(Code code) {
    return context.evalExpr(code);
  }

  public Object evalStmt(Code code) {
    return context.evalStmt(code);
  }
}

package patience.interpreter;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ExecutionTraceTree {
  private final String value;
  private final String name;
  private final List<ExecutionTraceTree> children;
  private final boolean leaf;
  
  public ExecutionTraceTree(String name, String value, boolean leaf, ExecutionTraceTree []children) {
    this(name, value, leaf, new ArrayList<ExecutionTraceTree>(Arrays.asList(children)));
  }
  
  public ExecutionTraceTree(String name, String value, boolean leaf, List<ExecutionTraceTree> children) {
    this.name = name;
    this.value = value;
    this.children = children;
    this.leaf = leaf;
   // System.out.println(this);
  }
  
  
  public String toString() {
    String result = name;
    if ( !leaf ) {
      result += "(";
      for ( ExecutionTraceTree tree : children) {
        result += "" + tree;
        result += ",";
      }
      result += ")";
    }
    return result + (value.equals("") ? "" : "[=" + value + "]");
  }

}
package patience.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Code implements Iterable<Code> {
  private final List<Code> nodes;
  private final String name;
  public enum Types { LEAF,    EMPTY,    UNARY,   BINARY,    MULTINARY, }

  private final Types type;
  private final int line;
  private DSLType dsltype;
  Iterator<Code> nextCode = iterator();
  
  public Code(String name, List<Code> nodes, int line) {
    this.name = name;
    this.nodes = nodes;
    this.line = line;
    this.type = getType(nodes);
  }

  public void setType(DSLType type) {
    this.dsltype = type;
  }
  
  protected Code.Types getType(List<Code> codes) {
    if ( codes == null     ) return Code.Types.LEAF; 
    if ( codes.size() == 0 ) return Code.Types.EMPTY;
    if ( codes.size() == 1 ) return Code.Types.UNARY;
    if ( codes.size() == 2 ) return Code.Types.BINARY;
    if ( codes.size() >  2 ) return Code.Types.MULTINARY;
    return Code.Types.LEAF;
  }
  
  public String getName() { return name; }
  public List<Code> getCodes() { return nodes; }
  public Types getType() { return type; }
  public int getLine() { return line; }
  public DSLType getDSLType() { return dsltype; }
  public String getID() {
    if ( this instanceof Leaf ) return ((Leaf)this).getValue().toString();
    return getName();
  }
  public Code get(int i) {
    return nodes.get(i);
  }
  
  public Iterator<Code> iterator() {
    return new Iterator<Code>() {
      int i = 0;
      public boolean hasNext() {
        return i < Code.this.nodes.size();
      }
      
      public Code next() {
        if ( !hasNext() ) return null;
        return Code.this.get(i++);
      }
      
      public void remove() {
        // do nothing
      }
    };
  }
  
  public Code next() {
    return nextCode.next();
  }
  
  public String toString() {
    if ( nodes == null ) return getID();
    else {
      String ret = "(" + getID() + " ";
      for ( Code child : nodes ) {
        ret += child.toString() + " ";
      }
      return ret.substring(0, ret.length() -1) + ")";
    }
  
  }

}
    
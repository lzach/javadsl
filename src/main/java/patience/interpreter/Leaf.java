package patience.interpreter;

import java.util.ArrayList;
import java.util.List;
public class Leaf extends Code {
  private Object value;
  public Leaf(String name, Object value, int line) {
    super(name, null, line);
    this.value = value;
  }
  
  public Object getValue() {
    return value;
  }
  
}
    
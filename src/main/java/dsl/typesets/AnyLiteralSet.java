package dsl.typesets;

import dsl.typeset.TypeSet;

public class AnyLiteralSet implements TypeSet {
  @Override
  public boolean contains(Object value) {
    return false;
  }

  @Override
  public boolean isSubset(TypeSet other) {
    return false;
  }

  @Override
  public boolean isEqual(TypeSet other) {
    return false;
  }
}

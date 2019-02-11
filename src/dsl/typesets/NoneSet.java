package dsl.typesets;

import dsl.typeset.TypeSet;

public class NoneSet implements TypeSet {
  @Override
  public boolean contains(Object value) {
    return false;
  }

  @Override
  public boolean isSubset(TypeSet other) {
    return true;

  }

  @Override
  public boolean isEqual(TypeSet other) {
    return other == this;
  }
}

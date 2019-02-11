package dsl.typesets;

import dsl.typeset.TypeSet;

public class AnySet implements TypeSet {
  @Override
  public boolean contains(Object value) {
    return true;
  }

  @Override
  public boolean isSubset(TypeSet other) {
    return other == this;

  }

  @Override
  public boolean isEqual(TypeSet other) {
    return other == this;
  }
}

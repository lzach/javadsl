package dsl.typeset;

import java.util.function.Predicate;

public class PredicateTypeSet<E> implements TypeSet<E> {
  private final Predicate<E> predicate;

  public PredicateTypeSet(Predicate<E> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean contains(E value) {
    return predicate.test(value);
  }

  @Override
  public boolean isSubset(TypeSet<E> other) {
    return false;
  }

  @Override
  public boolean isEqual(TypeSet<E> other) {
    return false;
  }
}

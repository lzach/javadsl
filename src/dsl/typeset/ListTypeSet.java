package dsl.typeset;

import java.util.Collection;

public class ListTypeSet<E> extends PredicateTypeSet<E> {
  private final Collection<E> members;

  public ListTypeSet(Collection<E> members) {
    super(members::contains);
    this.members = members;
  }

  @Override
  public boolean contains(E value) {
    return members.contains(value);
  }

  @Override
  public boolean isSubset(TypeSet<E> other) {
    return other.contains(members);
  }

  @Override
  public boolean isEqual(TypeSet<E> other) {
    return other instanceof ListTypeSet && ((ListTypeSet)other).members.equals(members);
  }
}

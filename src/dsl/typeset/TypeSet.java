package dsl.typeset;

import java.util.Collection;

public interface TypeSet<E> {
  boolean contains(E value);
  boolean isSubset(TypeSet<E> other);
  boolean isEqual(TypeSet<E> other);

  default boolean contains(Collection<E> values) {
    for ( E val : values ) {
      if ( !contains(val) ) return false;
    }
    return true;
  }


}

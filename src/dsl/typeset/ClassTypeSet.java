package dsl.typeset;

import java.util.Objects;

public class ClassTypeSet<E> extends PredicateTypeSet<E> {
  private final Class<E> cls;

  public ClassTypeSet(Class<E> cls) {
    super(obj -> cls.isInstance(obj));
    this.cls = cls;
  }

  @Override
  public boolean isSubset(TypeSet<E> other) {
    // subclasses aren't actually subset/subtypes, but close enough for us...
    return other instanceof ClassTypeSet && ((ClassTypeSet<E>)other).cls.isAssignableFrom(cls);
  }

  @Override
  public boolean isEqual(TypeSet<E> other) {
    return other instanceof ClassTypeSet && Objects.equals(((ClassTypeSet<E>) other).cls, cls);
  }

  public Class<E> getTypeClass() {
    return cls;
  }
}

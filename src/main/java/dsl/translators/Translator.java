package dsl.translators;

public interface Translator<E, T> {
  T translate(E ast);
}

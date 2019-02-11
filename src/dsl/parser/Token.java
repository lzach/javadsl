package dsl.parser;

public class Token {
  private final Type type;
  private final String value;
  private final int line;
  private final int col;

  public Token(Type type, String value) {
    this(type, value, 0, 0);
  }

  public Token(Type type, String value, int line, int col) {
    this.type = type;
    this.value = value;
    this.line = line;
    this.col = col;
  }

  public Type getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public int getLine() {

    return line;
  }

  public int getCol() {
    return col;
  }

  public enum Type {
    WS, ID, OP, NUM, STR, ERROR, EOF;
  }

  public String toString() {
    return "" + type + "('" + value + "', " + line + ", " + col + ")";

  }
}

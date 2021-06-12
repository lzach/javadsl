package dsl.parser;

import java.util.Objects;

public class Token {
  public static final Token UNKOWN = new Token(Type.ERROR, "");
  private final Type type;
  private final String value;
  private final int line;
  private final int col;
  private final String code;

  public Token(Type type, String value) {
    this(type, value, 0, 0,"");
  }

  public Token(Type type, String value, int line, int col) {
    this(type, value, line, col,"");
  }

  public Token(Type type, String value, int line, int col, String code) {
    this.type = type;
    this.value = value;
    this.line = line;
    this.col = col;
    this.code = code;
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

  public String errorString() {
    if (Objects.equals(code, "")) {
      return "";
    }
    String[] lines = code.split("\n");
    if ( lines.length < line ) {
      return lines[lines.length-1];
    }
    return lines[line] + "\n" + spaces(col-1) + "^";
  }

  private String spaces(int thisManySpaces) {
    StringBuilder ret = new StringBuilder();
    for ( int i = 0; i < thisManySpaces; ++i ) {
      ret.append(" ");
    }
    return ret.toString();
  }

  public enum Type {
    WS, ID, OP, NUM, STR, DOLLAR, ERROR, EOF;
  }

  public String toString() {
    return "" + type + "('" + value + "', " + line + ", " + col + ")";

  }
}

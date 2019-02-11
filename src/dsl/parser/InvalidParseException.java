package dsl.parser;

public class InvalidParseException extends RuntimeException {
  public InvalidParseException(String s) {
    super(s);
  }
}

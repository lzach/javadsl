package dsl.parser;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Tokenizer {
  private int _line = 1, _col = 1;
  private final Scanner scanner;
  private String pk = null;

  public Tokenizer(Scanner scanner) {
    this.scanner = scanner;
    scanner.useDelimiter("");
  }

  protected Token nextToken() {
    StringBuilder builder = new StringBuilder();
    int line = _line, col = _col;
    Token.Type type = null;
    boolean literalVal = false;
    String c = next();
    if ( c.equals("$") ) {
      literalVal = true;
      String value = (literalVal ? "$" : "") + builder.toString();

      return new Token(Token.Type.ID, value, line, col);
    }

    if ( c.matches("\\s") ) {
      type = Token.Type.WS;
      builder.append(c);
      while ( peek().matches("\\s") ) {
        c = next();
        builder.append(c);
      }
    } else if ( c.matches("[a-zA-Z]") ) {
      type = Token.Type.ID;
      builder.append(c);
      while ( peek().matches("[\\w\\-]") ) {
        builder.append(next());
      }
      while ( peek().matches("\\[")) {
        builder.append(next());
        c = next();
        builder.append(c);
        if (!Objects.equals(c, "]")) {
          type = Token.Type.ERROR;
        }
      }
    } else if ( c.matches("(\\(|\\)|:|=)") ) {
      type = Token.Type.OP;
      builder.append(c);
    } else if ( c.matches("\\d") ) {
      type = Token.Type.NUM;
      builder.append(c);
      if ( peek().matches("x") ) {
        builder.append(next());
      }
      while ( peek().matches("\\d")) {
        builder.append(next());
      }
      if ( peek().matches("\\.")) {
        builder.append(next());
      }
      if ( peek().matches("[eE]") ) {
        builder.append(next());
        if ( ! peek().matches("\\d") ) {
          type = Token.Type.ERROR;
        } else {
          while ( peek().matches("\\d") ) {
            builder.append(next());
          }
        }
      }

    } else if ( c.matches("['|\"]") ) {
      type = Token.Type.STR;
      String end = c;
      boolean escape = false;
      c = peek();
      while ( c.length() > 0 && (!c.matches(end) || escape) ) {
        next();
        if (Objects.equals(c, "\\")) {
          if ( escape ) {
            builder.append("\\");
          }
          escape = !escape;
        } else if ( escape ) {
          switch (c) {
            case "n":
              builder.append("\n");
              break;
            case "t":
              builder.append("\t");
              break;
            case "r":
              builder.append("\r");
              break;
            case "b":
              builder.append("\b");
              break;
            default:
              builder.append(c);
              break;
          }
          escape = false;
        } else {
          builder.append(c);
        }
        c = peek();
      }
      if ( !c.equals(end) ) {
        type = Token.Type.ERROR;
        builder.append(c);
      }
      next();
    } else if ( c.length() == 0 ) {
      type = Token.Type.EOF;
    } else {
      type = Token.Type.ERROR;
      builder.append(c);
    }
    if ( type == Token.Type.ERROR ) {
      while ( !eof() && !peek().matches("[\\s\\w\\d'\"():=]") ) {
        builder.append(next());
      }
    }
    String value = (literalVal ? "$" : "") + builder.toString();

    return new Token(type, value, line, col);
  }

  public Token get() {
    Token t = nextToken();
    while ( t.getType() == Token.Type.WS  ) {
      t = nextToken();
    }
    return t;
  }

  private boolean eof() {
    return peek().length() == 0;
  }

  protected String next() {
    String tmp = peek();
    pk = advance();
    _col += 1;
    if (Objects.equals(pk, "\n")) {
      _line += 1;
      _col = 1;
    }
    return tmp;
  }

  protected String peek() {
    if (pk == null) {
      pk = advance();
    }
    return pk;
  }

  protected String advance() {
    try {
      return scanner.next();
    } catch ( NoSuchElementException e) {
      return "";
    }
  }

  protected char nextChar() {
    String tmp = scanner.next();
    if ( tmp.length() == 0 ) {
      return 0;
    }
    return tmp.charAt(0);
  }
}

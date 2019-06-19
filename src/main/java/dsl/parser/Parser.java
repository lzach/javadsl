package dsl.parser;

import dsl.Type;
import dsl.ast.AST;
import dsl.ast.ASTBuilder;
import dsl.ast.InvalidAstException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static dsl.parser.Token.Type.*;

public class Parser {

  private Token pk = null;
  private final Tokenizer tokenizer;
  private final Map<String, Type> symbols = new HashMap<>();

  public Parser(String file) throws FileNotFoundException {
    this(new File(file));
  }

  public Parser( File file) throws FileNotFoundException {
    this(new Scanner(file));
  }

  public Parser(Scanner scanner) {
    this.tokenizer = new Tokenizer(scanner);
  }

  public AST parse() {
    AST ast = getNode();
    expect(Token.Type.EOF);
    return ast;
  }

  public AST getNode() {
    ASTBuilder builder = new ASTBuilder();
    expect(OP, "(");
    Token name = expect(Token.Type.ID);
    builder.setName(name.getValue());
    builder.setToken(name);
    if (has(Token.Type.ID)) {
      Token id = next();
      if ( has(OP, ":") ) {
        next();
        builder.add(id.getValue(), getValue());
        while ( has(Token.Type.ID)) {
          id = next();
          expect(OP, ":");
          builder.add(id.getValue(), getValue());
        }
      } else {
        builder.add(literal(id));
        while (!eof () && !has(OP, ")")) {
          builder.add(getValue());
        }
      }
    } else {
      while (!eof () && !has(OP, ")")) {
        builder.add(getValue());
      }
    }
    expect(OP, ")");
    return builder.create();
  }

  protected AST literal(Token value) {
    String typ = "";
    Object val = null;
    switch (value.getType()) {
      case ID:
        typ = "ID";
        val = value.getValue();
        //if ( symbols.containsKey(val) ) {
        //  return AST.create((String)val);
        //}
        //Map<String, AST> typeMap = new HashMap<>();
        //typeMap.put("reference", AST.create("IDLit", val));
        //return new AST("NameLit", typeMap);
	break;
      case NUM:
        if ( value.getValue().matches("[eE.]") ) {
          typ = "Float";
          val = Float.parseFloat(value.getValue());
        } else {
          typ = "Int";
          val = Integer.parseInt(value.getValue());
        }
        break;
      case STR:
        typ = "String";
        val = value.getValue();
        return AST.STRLit(val.toString());
//        break;
      default:
        throw new InvalidAstException("Expected a literal value");
    }
    return AST.create(typ + "Lit", val, value);
  }

  protected boolean eof() {
    return peek().getType() == Token.Type.EOF;
  }


  public AST getValue() {
    if ( has(OP, "(")) {
      return getNode();
    } else {
      return literal(expect(STR, NUM, ID));
    }
  }

  public Token expect(Token.Type ... token) {
    if ( has(token) ) {
      return next();
    }
    throw new InvalidParseException("Expected " + Arrays.toString(token) + ", got " + peek().getType() + "('" + peek().getValue() + "') at " + peek().getLine() + ":" + peek().getCol());
  }

  public Token expect(Token.Type token, String value) {
    if ( has(token, value)) {
      return next();
    }
    throw new InvalidParseException("Expected " + token + "(" + value + ")" + ", got " + peek());
  }

  public boolean has(Token.Type ... tokens) {
    Token t = peek();
    for ( Token.Type tType : tokens ) {
      if (t.getType() == tType) {
        return true;
      }
    }
    return false;
  }

  public boolean has(Token.Type token) {
    Token t = peek();
    return t.getType() == token;
  }

  public boolean has(Token.Type token, String value) {
    Token t = peek();
    return t.getType() == token && Objects.equals(t.getValue(), value);
  }

  protected Token peek() {
    if ( pk == null ) {
      pk = tokenizer.get();
    }
    return pk;
  }

  protected Token next() {
    Token tmp = peek();
    pk = tokenizer.get();
    return tmp;
  }

  public static void main(String[] args) throws FileNotFoundException {
//    Parser p = new Parser(DomainLanguage.getLanguage().combine(LiteralDomain.getLanguage()), new Scanner("(CreateRecord name:value members:(VarDecls (VarDecl name:test type:int default:0) (VarDecl name:test2 type:string default:'test string')))"));
    Parser p = new Parser(new File("examples/definition.dsl"));
    System.out.println(p.parse());
  }
}
